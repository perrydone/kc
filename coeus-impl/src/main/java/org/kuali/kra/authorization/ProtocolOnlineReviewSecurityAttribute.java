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
package org.kuali.kra.authorization;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.rice.kew.api.KewApiServiceLocator;
import org.kuali.rice.kew.api.action.ActionItem;
import org.kuali.rice.kew.api.actionlist.ActionListService;
import org.kuali.rice.kew.api.document.Document;
import org.kuali.rice.kew.framework.document.security.DocumentSecurityAttribute;
import org.kuali.rice.kns.service.DocumentHelperService;


public class ProtocolOnlineReviewSecurityAttribute implements DocumentSecurityAttribute {

    private DocumentHelperService documentHelperService;
    private ActionListService actionListService;

    /*
     * Cannot use canopen doc perm here because it does not exist for ONLR docs
     */
    @Override
    public boolean isAuthorizedForDocument(String principalId, Document document) {
        boolean retVal= false;
        String documentId = document.getDocumentId();
        // get the action items for this document and check if any of them have the same principal id as the current user
        List<ActionItem> actionItemsForDocument = this.getActionListService().getAllActionItems(documentId);
        for(ActionItem actionItem: actionItemsForDocument) {
            if(StringUtils.equals(principalId, actionItem.getPrincipalId())) {
                retVal = true;
                break;                    
            }
        }
        return retVal;
    }
    
    protected ActionListService getActionListService() {
        if(this.actionListService == null) {
            this.actionListService = KewApiServiceLocator.getActionListService();
        }
        return this.actionListService;
    }

}
