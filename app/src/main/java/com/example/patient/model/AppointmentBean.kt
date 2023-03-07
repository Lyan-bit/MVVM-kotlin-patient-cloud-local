package com.example.patient.model

import android.content.Context
import com.example.patient.viewmodel.AppointmentViewModel

class AppointmentBean(c: Context) {

    private var model: AppointmentViewModel = AppointmentViewModel.getInstance(c)

    private var appointmentId = ""
    private var code = ""
    private var patientId = ""

    private var errors = ArrayList<String>()

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
	        
	 	 	           if (appointmentId != "") {}
	else {
	 	  errors.add("appointmentId cannot be empty")
	}
          if (code != "") {}
	else {
	 	  errors.add("code cannot be empty")
	}

	        return errors.size > 0
	    }
	    
	    fun createAppointment() {
	        model.createAppointment(AppointmentVO(appointmentId, code))
	        resetData()
	    }

    fun isListAppointmentError(): Boolean {
	        errors.clear()
	        return errors.size > 0
	    }

     fun editAppointment() {
		     model.editAppointment(AppointmentVO(appointmentId, code))
		     resetData()
		 }
		       
		 fun isEditAppointmentError(allAppointmentappointmentIds: List<String>): Boolean {
	        
	        errors.clear()
			
			if (!allAppointmentappointmentIds.contains(appointmentId)) {
				errors.add("The appointmentId is not exist")
		    }
			          if (appointmentId != "") {}
	         else {
	               errors.add("appointmentId cannot be empty")
	         }
	                  if (code != "") {}
	         else {
	               errors.add("code cannot be empty")
	         }
	        
       return errors.size > 0
   }

	    fun deleteAppointment() {
	        model.deleteAppointment(appointmentId)
	        resetData()
	    }
	    
	    fun isDeleteAppointmentError(allAppointmentappointmentIds: List<String>): Boolean {
	         errors.clear()
			 if (!allAppointmentappointmentIds.contains(appointmentId)) {
			    errors.add("The appointmentId is not exist")
	         }
	         return errors.size > 0
		}    


		fun isSearchAppointmentIdError(allAppointmentIds: List<String>): Boolean {
    	   errors.clear()
   	       if (!allAppointmentIds.contains(appointmentId)) {
    	       errors.add("The appointmentId is not exist")
    	   }
           return errors.size > 0
    }

    fun errors(): String {
        return errors.toString()
    }

}

