/*
 * Copyright (c) 2023 Volkswagen AG
 * Copyright (c) 2023 Contributors to the Eclipse Foundation
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

package org.eclipse.tractusx.puris.backend.stock.domain.repository;

import org.eclipse.tractusx.puris.backend.masterdata.domain.model.Material;
import org.eclipse.tractusx.puris.backend.masterdata.domain.model.Partner;
import org.eclipse.tractusx.puris.backend.stock.domain.model.ReportedProductItemStock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportedProductItemStockRepository extends ItemStockRepository<ReportedProductItemStock> {
    List<ReportedProductItemStock> findByPartnerAndMaterial(Partner partner, Material material);

    List<ReportedProductItemStock> findByPartner(Partner partner);

    List<ReportedProductItemStock> findByMaterial(Material material);

    List<ReportedProductItemStock> findByMaterial_OwnMaterialNumber(String ownMaterialNumber);

    List<ReportedProductItemStock> findByPartner_Bpnl(String partnerBpnl);

    List<ReportedProductItemStock> findByPartner_BpnlAndMaterial_OwnMaterialNumber(String partnerBpnl, String ownMaterialNumber);

    @Override
    default List<ReportedProductItemStock> find(Partner partner, Material material) {
        return findByPartnerAndMaterial(partner, material);
    }

    @Override
    default List<ReportedProductItemStock> find(Partner partner) {
        return findByPartner(partner);
    }

    @Override
    default List<ReportedProductItemStock> find(Material material) {
        return findByMaterial(material);
    }

    @Override
    default List<ReportedProductItemStock> findOwnMatNbr(String ownMaterialNumber) {
        return findByMaterial_OwnMaterialNumber(ownMaterialNumber);
    }

    @Override
    default List<ReportedProductItemStock> findPartnerBpnl(String partnerBpnl) {
        return findByMaterial_OwnMaterialNumber(partnerBpnl);
    }

    @Override
    default List<ReportedProductItemStock> findPartnerBpnlAndOwnMatNbr(String partnerBpnl, String ownMaterialNumber) {
        return findByPartner_BpnlAndMaterial_OwnMaterialNumber(partnerBpnl, ownMaterialNumber);
    }
}
