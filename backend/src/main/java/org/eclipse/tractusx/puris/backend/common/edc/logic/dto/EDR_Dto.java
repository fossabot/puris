package org.eclipse.tractusx.puris.backend.common.edc.logic.dto;

import org.eclipse.tractusx.puris.backend.common.edc.logic.service.EndpointDataReferenceService;

/**
 * An internal, immutable Dto class used by the {@link EndpointDataReferenceService}
 * It contains an authKey, authCode and endpoint.
 *
 * @param authKey  This defines the key, under which the
 *                 authCode is to be sent to the data plane.
 *                 For example: "Authorization"
 * @param authCode This is the secret key to be sent
 *                 to the data plane.
 * @param endpoint The address of the data plane that has
 *                 to handle the consumer pull.
 */
public record EDR_Dto(String authKey, String authCode, String endpoint) {
}
