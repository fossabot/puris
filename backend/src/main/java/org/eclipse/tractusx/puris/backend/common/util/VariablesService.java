/*
 * Copyright (c) 2023, 2024 Volkswagen AG
 * Copyright (c) 2023, 2024 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e.V.
 * (represented by Fraunhofer ISST)
 * Copyright (c) 2023, 2024 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package org.eclipse.tractusx.puris.backend.common.util;

import lombok.Getter;

import org.eclipse.tractusx.puris.backend.common.edc.logic.dto.datatype.DT_ItemStockApiMethodEnum;
import org.eclipse.tractusx.puris.backend.common.edc.logic.dto.datatype.DT_PlannedProductionApiMethodEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Getter
@Service
/**
 * This class contains the relevant
 */
public class VariablesService {

    @Value("${server.port}")
    /**
     * The port used by this apps server application.
     */
    private String serverPort;
    @Value("${puris.baseurl}")
    private String purisBaseUrl;
    @Value("${puris.demonstrator.role}")
    /**
     * Must be set to "CUSTOMER" or "SUPPLIER" if
     * you want to start with some initial settings
     * defined in the DataInjectionCommandLineRunner
     */
    private String demoRole;

    @Value("${puris.baseurl}" + "catena/edrendpoint")
    /**
     * The edrEndpoint to be used during consumer pull asset transfers.
     */
    private String edrEndpoint;
    @Value("${puris.edr.deletiontimer}")
    /**
     * The number of minutes before received authentication data
     * in the context of a consumer pull is removed from memory
     */
    private long edrTokenDeletionTimer;
    @Value("${puris.baseurl}" + "catena/item-stock/request")
    /**
     * The url under which this application's request endpoint can
     * be reached by external machines.
     */
    private String itemStockRequestServerEndpoint;
    @Value("${puris.assets.itemstock.requestid}")
    /**
     * The assetId that shall be assigned to the request API
     * during asset creation.
     */
    private String itemStockRequestApiAssetId;
    @Value("${puris.baseurl}" + "catena/item-stock/response")
    /**
     * The url under which this application's response endpoint can
     * be reached by external machines.
     */
    private String itemStockResponseServerEndpoint;
    @Value("${puris.assets.itemstock.responseid}")
    /**
     * The assetId that shall be assigned to the request API
     * during asset creation.
     */
    private String itemStockResponseApiAssetId;
    @Value("${puris.assets.itemstock.statusrequestid}")
    /**
     * The assetId that shall be assigned to the status-request API
     * during asset creation.
     */
    private String itemStockStatusRequestApiAssetId;
    @Value("${puris.baseurl}" + "catena/item-stock/status")
    /**
     * The url under which this application's status-request endpoint
     * can be reached by external machines.
     */
    private String itemStockStatusRequestServerEndpoint;
    @Value("${puris.baseurl}" + "catena/planned-production/request")
    /**
     * The url under which this application's request endpoint can
     * be reached by external machines.
     */
    private String plannedProductionRequestServerEndpoint;
    @Value("${puris.assets.plannedproduction.requestid}")
    /**
     * The assetId that shall be assigned to the request API
     * during asset creation.
     */
    private String plannedProductionRequestApiAssetId;
    @Value("${puris.baseurl}" + "catena/planned-production/response")
    /**
     * The url under which this application's response endpoint can
     * be reached by external machines.
     */
    private String plannedProductionResponseServerEndpoint;
    @Value("${puris.assets.plannedproduction.responseid}")
    /**
     * The assetId that shall be assigned to the request API
     * during asset creation.
     */
    private String plannedProductionResponseApiAssetId;
    @Value("${puris.assets.plannedproduction.statusrequestid}")
    /**
     * The assetId that shall be assigned to the status-request API
     * during asset creation.
     */
    private String plannedProductionStatusRequestApiAssetId;
    @Value("${puris.baseurl}" + "catena/planned-production/status")
    /**
     * The url under which this application's status-request endpoint
     * can be reached by external machines.
     */
    private String plannedProductionStatusRequestServerEndpoint;
    @Value("${puris.frameworkagreement.use}")
    /**
     * A flag that signals whether a framework policy
     * shall be used as contract policy for your api assets.
     */
    private boolean useFrameworkPolicy;
    @Value("${puris.frameworkagreement.credential}")
    /**
     * The name of the framework agreement to be used.
     */
    private String purisFrameworkAgreement;
    @Value("${puris.api.key}")
    /**
     * The key for accessing the api.
     */
    private String apiKey;

    @Value("${puris.dtr.url}")
    /**
     * The url of your decentralized DTR
     */
    private String dtrUrl;

    @Value("${puris.baseurl}" + "catena/parttypeinformation")
    private String parttypeInformationServerendpoint;

    @Value("${puris.generatematerialcatenaxid}")
    /**
     * A flag that signals whether the MaterialService
     * should auto-generate a CatenaXId for a newly
     * created material.
     */
    private boolean generateMaterialCatenaXId;

    @Value("${edc.controlplane.key}")
    /**
     * The api key of your control plane
     */
    private String edcApiKey;
    @Value("${edc.controlplane.management.url}")
    /**
     * Your control plane's management url
     */
    private String edcManagementUrl;
    @Value("${edc.controlplane.protocol.url}")
    /**
     * Your control plane's protocol url
     */
    private String edcProtocolUrl;
    @Value("${edc.dataplane.public.url}")
    /**
     * Your data plane's public url
     */
    private String edcDataplanePublicUrl;


    @Value("${own.bpnl}")
    /**
     * The BPNL that was assigned to you.
     */
    private String ownBpnl;
    @Value("${own.name}")
    /**
     * A human-readable description of yourself, e.g.
     * the name of your company.
     */
    private String ownName;
    @Value("${own.bpns}")
    /**
     * A BPNS that was assigned to you.
     */
    private String ownDefaultBpns;
    @Value("${own.site.name}")
    /**
     * A human-readable description of the site that you referenced in
     * the ownDefaultBpns.
     */
    private String ownDefaultSiteName;
    @Value("${own.bpna}")
    /** A BPNA that was assigned to you. If you initialised the
     * ownDefaultBpns variable, then it must be a BPNA that is associated
     * to that BPNS.
     */
    private String ownDefaultBpna;
    @Value("${own.streetandnumber}")
    /**
     * The street and number associated to the ownDefaultBpna
     */
    private String ownDefaultStreetAndNumber;
    @Value("${own.zipcodeandcity}")
    /**
     * The zip code and name of the city associated to the ownDefaultBpna
     */
    private String ownDefaultZipCodeAndCity;
    @Value("${own.country}")
    /**
     * The country in which your ownDefaultBpna-address is located.
     */
    private String ownDefaultCountry;


    /**
     * Returns the asset-id as defined in the properties file for the given api method
     * under request.apiassetid or response.apiassetid respectively.
     * @param method
     * @return the asset-id
     */
    public String getItemStockApiAssetId(DT_ItemStockApiMethodEnum method) {
        if(itemStockResponseApiAssetId == null || itemStockRequestApiAssetId == null || itemStockStatusRequestApiAssetId == null) {
            throw new RuntimeException("You must define puris.assets.itemstock.requestid, puris.assets.itemstock.responseid " +
                "and puris.assets.itemstock.statusrequestid in properties file");
        }
        switch (method) {
            case REQUEST: return itemStockRequestApiAssetId;
            case RESPONSE: return itemStockResponseApiAssetId;
            case STATUS_REQUEST: return itemStockStatusRequestApiAssetId;
            default: throw new RuntimeException("Unknown Api Method: " + method);
        }
    }
    
    public String getPlannedProductionApiAssetId(DT_PlannedProductionApiMethodEnum method) {
        if(plannedProductionResponseApiAssetId == null || plannedProductionRequestApiAssetId == null || plannedProductionStatusRequestApiAssetId == null) {
            throw new RuntimeException("You must define puris.assets.plannedproduction.requestid, puris.assets.plannedproduction.responseid " +
                "and puris.assets.plannedproduction.statusrequestid in properties file");
        }
        switch (method) {
            case REQUEST: return plannedProductionRequestApiAssetId;
            case RESPONSE: return plannedProductionResponseApiAssetId;
            case STATUS_REQUEST: return plannedProductionStatusRequestApiAssetId;
            default: throw new RuntimeException("Unknown Api Method: " + method);
        }
    }
}
