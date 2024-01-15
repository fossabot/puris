package org.eclipse.tractusx.puris.backend.stock.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.tractusx.puris.backend.common.api.domain.model.datatype.DT_RequestStateEnum;
import org.eclipse.tractusx.puris.backend.masterdata.domain.model.Partner;
import org.eclipse.tractusx.puris.backend.masterdata.logic.service.PartnerService;
import org.eclipse.tractusx.puris.backend.stock.domain.model.ItemStockRequestMessage;
import org.eclipse.tractusx.puris.backend.stock.logic.dto.ItemStockResponseDto;
import org.eclipse.tractusx.puris.backend.stock.logic.service.ItemStockRequestMessageService;
import org.eclipse.tractusx.puris.backend.stock.logic.service.ItemStockResponseApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.regex.Pattern;

@RestController
@RequestMapping("item-stock")
@Slf4j
public class ItemStockResponseApiController {
    @Autowired
    private ItemStockRequestMessageService itemStockRequestMessageService;
    @Autowired
    private PartnerService partnerService;
    @Autowired
    private ItemStockResponseApiService itemStockResponseApiService;
    @Autowired
    private ExecutorService executorService;
    private final Pattern bpnlPattern = Pattern.compile(Partner.BPNL_REGEX);


    @Operation(description = "This endpoint receives the item stock response messages. ")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "The response message was accepted"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "422", description = "The related message id does not match any open request")
    })
    @PostMapping("response")
    public ResponseEntity<ResponseReactionMessageDto> postMapping(@RequestBody ItemStockResponseDto responseDto) {
        log.info("Received response: \n" + responseDto);
        if (responseDto.getHeader() == null || responseDto.getHeader().getMessageId() == null || responseDto.getHeader().getRelatedMessageId() == null
            || responseDto.getHeader().getReceiverBpn() == null || responseDto.getHeader().getSenderBpn() == null
            || !bpnlPattern.matcher(responseDto.getHeader().getSenderBpn()).matches()
            || !bpnlPattern.matcher(responseDto.getHeader().getReceiverBpn()).matches()) {
            return ResponseEntity.status(400).body(new ResponseReactionMessageDto(responseDto.getHeader().getMessageId()));
        }

        Partner partner = partnerService.findByBpnl(responseDto.getHeader().getSenderBpn());
        if (partner == null) {
            log.error("Unknown partner in response dto: \n" + responseDto);
            return ResponseEntity.status(400).body(new ResponseReactionMessageDto(responseDto.getHeader().getMessageId()));
        }
        var initialRequest = itemStockRequestMessageService.find(
            new ItemStockRequestMessage.Key(responseDto.getHeader().getRelatedMessageId(),
                responseDto.getHeader().getReceiverBpn(),responseDto.getHeader().getSenderBpn()));
        if (initialRequest == null || initialRequest.getState() != DT_RequestStateEnum.Requested) {
            log.error("Response dto does not match any open request: \n" + responseDto);
            return ResponseEntity.status(422).body(new ResponseReactionMessageDto(responseDto.getHeader().getMessageId()));
        }
        executorService.submit(() -> itemStockResponseApiService.consumeResponse(responseDto, partner, initialRequest));
        return ResponseEntity.status(200).body(new ResponseReactionMessageDto(responseDto.getHeader().getMessageId()));
    }

    private record ResponseReactionMessageDto(UUID messageId) {
    }
}
