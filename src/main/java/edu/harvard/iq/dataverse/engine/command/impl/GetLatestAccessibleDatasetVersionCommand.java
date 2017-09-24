/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.harvard.iq.dataverse.engine.command.impl;

import edu.harvard.iq.dataverse.Dataset;
import edu.harvard.iq.dataverse.DatasetVersion;
import edu.harvard.iq.dataverse.engine.command.AbstractCommand;
import edu.harvard.iq.dataverse.engine.command.CommandContext;
import edu.harvard.iq.dataverse.engine.command.DataverseRequest;
import edu.harvard.iq.dataverse.engine.command.RequiredPermissions;
import edu.harvard.iq.dataverse.engine.command.exception.CommandException;
import edu.harvard.iq.dataverse.engine.command.exception.PermissionException;

/**
 * Get the latest version of a dataset a user can view.
 * @author Naomi
 */
// No permission needed to view published dvObjects
@RequiredPermissions({})
public class GetLatestAccessibleDatasetVersionCommand extends AbstractCommand<DatasetVersion>{
    private final Dataset ds;

    public GetLatestAccessibleDatasetVersionCommand(DataverseRequest aRequest, Dataset anAffectedDataset) {
        super(aRequest, anAffectedDataset);
        ds = anAffectedDataset;
    }

    @Override
    public DatasetVersion execute(CommandContext ctxt) throws CommandException {
        DatasetVersion d = null;
        
        try {
            d = ctxt.engine().submit(new GetDraftDatasetVersionCommand(getRequest(), ds));
        } catch(PermissionException ex) {}
        
        if (d == null || d.getId() == null) {
            d = ctxt.engine().submit(new GetLatestPublishedDatasetVersionCommand(getRequest(),ds));
        }
        
        return d;
    }
    
    
    
}
