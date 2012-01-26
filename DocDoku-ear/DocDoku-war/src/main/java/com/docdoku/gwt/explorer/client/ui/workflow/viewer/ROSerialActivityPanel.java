/*
 * DocDoku, Professional Open Source
 * Copyright 2006, 2007, 2008, 2009, 2010, 2011, 2012 DocDoku SARL
 *
 * This file is part of DocDoku.
 *
 * DocDoku is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DocDoku is distributed in the hope that it will be useful,  
 * but WITHOUT ANY WARRANTY; without even the implied warranty of  
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the  
 * GNU General Public License for more details.  
 *  
 * You should have received a copy of the GNU General Public License  
 * along with DocDoku.  If not, see <http://www.gnu.org/licenses/>.  
 */

package com.docdoku.gwt.explorer.client.ui.workflow.viewer;

import com.docdoku.gwt.explorer.shared.SerialActivityDTO;
import com.docdoku.gwt.explorer.shared.TaskDTO;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class ROSerialActivityPanel extends ROActivityPanel {

    private InteractiveTaskPanel interactive = null;

    public ROSerialActivityPanel(SerialActivityDTO model, String visitorName, boolean currentActivity, int step, TaskListener l) {
        HorizontalPanel mainPanel = new HorizontalPanel();
        int i = 0;
        boolean found = false;
        for (TaskDTO t : model.getTasks()) {
            ROTaskPanel taskPanel = null;
            if (!model.isStopped() && !found && t.getWorker().getName().equals(visitorName) && currentActivity && (t.getStatus() == TaskDTO.Status.NOT_STARTED || t.getStatus() == TaskDTO.Status.IN_PROGRESS)) {
                InteractiveTaskPanel tmp = new InteractiveTaskPanel(t, true, step, i);
                tmp.addTaskListener(l);
                taskPanel = tmp;
                found = true;
                interactive = tmp;
            } else {
                if (!found && (t.getStatus() == TaskDTO.Status.NOT_STARTED || t.getStatus() == TaskDTO.Status.IN_PROGRESS)){
                    found =true ;
                }
                taskPanel = new ROTaskPanel(t);
            }

            mainPanel.add(taskPanel);
            i++;
        }
        initWidget(mainPanel);
    }

    public void hideAllPopups(){
        if (interactive != null){
            interactive.hideOptions();
        }
    }

}
