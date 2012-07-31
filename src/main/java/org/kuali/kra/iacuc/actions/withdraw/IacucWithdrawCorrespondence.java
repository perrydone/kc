/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.iacuc.actions.withdraw;

import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.print.IacucProtocolPrintWatermark;
import org.kuali.kra.protocol.actions.correspondence.AbstractProtocolActionsCorrespondence;
import org.kuali.kra.protocol.actions.print.ProtocolPrintWatermark;

/**
 * 
 * This class deals with the template and the printing for the withdrawl protocol action.
 */
public class IacucWithdrawCorrespondence extends AbstractProtocolActionsCorrespondence {
    
    public static final long serialVersionUID = 1234567890;
    @Override
    public String getProtocolActionType() {
        return IacucProtocolActionType.IACUC_WITHDRAWN;
    }
    @Override
    protected ProtocolPrintWatermark getNewProtocolPrintWatermarkInstanceHook() {
        return new IacucProtocolPrintWatermark();
    }

}
