package com.example.patient.model

import android.content.Context
import com.example.patient.viewmodel.AppointmentViewModel

class AppointmentBean(c: Context) {

    private var model: AppointmentViewModel = AppointmentViewModel.getInstance(c)

    private var appointmentId = ""
    private var code = ""
    private var patientId = ""

    private var errors = ArrayList<String>()
     private var checkParameter = "is not exist"

    fun setAppointmentId(appointmentIdx: String) {
	 appointmentId = appointmentIdx
    }
    
    fun setCode(codex: String) {
	 code = codex
    }
    

    fun setPatientId(patientIdx : String) {
	patientId = patientIdx
    }

    fun resetData() {
	  appointmentId = ""
	  code = ""
    }
    
    fun isCreateAppointmentError(): Boolean {
	        
	 errors.clear()
	        
	 	 	           if (appointmentId != "") {
				   //ok
				   }
	else {
	 	  errors.add("appointmentId cannot be empty")
	}
          if (code != "") {
	  //ok
	  }
	else {
	 	  errors.add("code cannot be empty")
	}

	        return errors.isNotEmpty()
	    }
	    
	    fun createAppointment() {
	        model.createAppointment(AppointmentVO(appointmentId, code))
	        resetData()
	    }

    fun isListAppointmentError(): Boolean {
	        errors.clear()
		//if Statement
	        return errors.isNotEmpty()
	    }

     fun editAppointment() {
		     model.editAppointment(AppointmentVO(appointmentId, code))
		     resetData()
		 }
		       
		 fun isEditAppointmentError(allAppointmentappointmentIds: List<String>): Boolean {
	        
	        errors.clear()
			
			if (!allAppointmentappointmentIds.contains(appointmentId)) {
				errors.add("appointmentId" + checkParameter)
		    }
			          if (appointmentId != "") {
				  //ok
				  }
	         else {
	               errors.add("appointmentId cannot be empty")
	         }
	                  if (code != "") {
			  //ok
			  }
	         else {
	               errors.add("code cannot be empty")
	         }
	        
       return errors.isNotEmpty()
   }

	    fun deleteAppointment() {
	        model.deleteAppointment(appointmentId)
	        resetData()
	    }
	    
	    fun isDeleteAppointmentError(allAppointmentappointmentIds: List<String>): Boolean {
	         errors.clear()
			 if (!allAppointmentappointmentIds.contains(appointmentId)) {
			    errors.add("appointmentId" + checkParameter)
	         }
	         return errors.isNotEmpty()
		}    


		fun isSearchAppointmentIdError(allAppointmentIds: List<String>): Boolean {
    	   errors.clear()
   	       if (!allAppointmentIds.contains(appointmentId)) {
    	       errors.add("appointmentId" + checkParameter)
    	   }
           return errors.isNotEmpty()
    }

    fun errors(): String {
        return errors.toString()
    }

}

