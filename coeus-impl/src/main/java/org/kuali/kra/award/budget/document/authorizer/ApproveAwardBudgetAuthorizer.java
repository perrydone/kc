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
package org.kuali.kra.award.budget.document.authorizer;

import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.budget.document.authorization.AwardBudgetTask;

/**
 * The AwardBudget Modify Authorizer checks to see if the user has 
 * the necessary permission to modify a specific budget.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ApproveAwardBudgetAuthorizer extends BudgetAuthorizer {

    private KcWorkflowService kraWorkflowService;

    @Override
    public boolean isAuthorized(String userId, Task task) {
        AwardBudgetTask budgetTask = (AwardBudgetTask) task;
        AwardBudgetDocument budgetDocument = budgetTask.getAwardBudgetDocument();

        return kraWorkflowService.isEnRoute(budgetDocument) && kraWorkflowService.hasWorkflowPermission(userId, budgetDocument);
    }

    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }
}
