/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.s2s;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.krad.lookup.LookupForm;
import org.kuali.rice.krad.lookup.LookupableImpl;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Component("s2sOpportunityLookupable")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S2sOpportunityLookupable extends LookupableImpl {

    private static final Log LOG = LogFactory.getLog(S2sOpportunityLookupable.class);

    @Autowired
    @Qualifier("s2sOpportunityLookupKradKnsHelperService")
    private S2sOpportunityLookupKradKnsHelperService s2sOpportunityLookupKradKnsHelperService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Override
    public List<?> performSearch(LookupForm form, Map<String, String> searchCriteria, boolean unbounded) {

        final String providerCode = searchCriteria.get(Constants.PROVIDER_CODE);
        final String cfdaNumber = searchCriteria.get(Constants.CFDA_NUMBER);
        final String opportunityId = searchCriteria.get(Constants.OPPORTUNITY_ID);

        List<?> opportunities = s2sOpportunityLookupKradKnsHelperService.performSearch(providerCode, cfdaNumber, opportunityId);
        if (CollectionUtils.isEmpty(opportunities)) {
            addNotFoundMessage();
        }

        return opportunities;
    }

    protected void addNotFoundMessage() {
        MessageMap messageMap = globalVariableService.getMessageMap();
        messageMap.putInfoForSectionId(UifConstants.MessageKeys.LOOKUP_RESULT_MESSAGES,
                RiceKeyConstants.INFO_LOOKUP_RESULTS_NONE_FOUND);
    }

    public S2sOpportunityLookupKradKnsHelperService getS2sOpportunityLookupKradKnsHelperService() {
        return s2sOpportunityLookupKradKnsHelperService;
    }

    public void setS2sOpportunityLookupKradKnsHelperService(S2sOpportunityLookupKradKnsHelperService s2sOpportunityLookupKradKnsHelperService) {
        this.s2sOpportunityLookupKradKnsHelperService = s2sOpportunityLookupKradKnsHelperService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
