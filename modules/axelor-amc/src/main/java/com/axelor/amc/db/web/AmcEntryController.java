package com.axelor.amc.db.web;

import com.axelor.amc.db.AmcEntry;
import com.axelor.gst.db.Sequence;
import com.axelor.gst.db.repo.SequenceRepository;
import com.axelor.gst.db.service.SequenceService;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class AmcEntryController {
	
	  @Inject SequenceService sequenceService;
	  @Inject SequenceRepository sequenceRepo;

	  public void setReferenceInAmcEntry(ActionRequest request, ActionResponse response) {
	    AmcEntry amcEntry = request.getContext().asType(AmcEntry.class);

	    if (amcEntry.getReference() == null) {
	      Sequence sequence =
	          sequenceRepo
	              .all()
	              .filter("self.model.name = :model")
	              .bind("model", AmcEntry.class.getSimpleName())
	              .fetchOne();
	      String seq = sequenceService.getSequence(sequence, true);
	      if (seq != null) {
	    	  amcEntry.setReference(seq);
	        response.setValue("reference", seq);
	      } else {
	        response.setError("Sequence Not Available");
	      }
	    }
	  }


}
