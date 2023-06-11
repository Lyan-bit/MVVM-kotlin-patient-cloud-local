package com.example.patient.viewmodel

import android.content.Context
import com.example.patient.database.FirebaseDB
import com.example.patient.model.Patient
import com.example.patient.model.PatientVO
import java.util.ArrayList


class PatientViewModel private constructor(context: Context) {

    private var cdb: FirebaseDB = FirebaseDB.getInstance()
    private var currentPatient: PatientVO? = null
    private var currentPatients: ArrayList<PatientVO> = ArrayList()


    companion object {
        private var instance: PatientViewModel? = null
        fun getInstance(context: Context): PatientViewModel {
            return instance ?: PatientViewModel(context)
        }
    }
    
    fun createPatient(x: PatientVO) {
		editPatient(x)
	}
				    
    fun editPatient(x: PatientVO) {
		var obj = getPatientByPK(x.patientId)
		if (obj == null) {
			obj = Patient.createByPKPatient(x.patientId)
		}
			
		  obj.patientId = x.patientId
		  obj.name = x.name
		  obj.appointmentId = x.appointmentId
		cdb.persistPatient(obj)
		currentPatient = x
	}
		
	fun searchPatientById(search: String) : PatientVO {
		var res = PatientVO()
		for (x in currentPatients.indices) {
			if ( currentPatients[x].patientId.toString() == search)
			res = currentPatients[x]
		}
		return res
	}
	
	fun deletePatient(id: String) {
			  val obj = getPatientByPK(id)
			  if (obj != null) {
			      cdb.deletePatient(obj)
			          Patient.killPatient(id)
			      }
			  currentPatient = null	
		}
				    
    fun setSelectedPatient(x: PatientVO) {
			  currentPatient = x
	}

    fun addPatientattendsAppointment(appointmentId: String, patientId: String) {
	      var obj = getPatientByPK(patientId)
	      if (obj == null) {
	          obj = Patient.createByPKPatient(patientId)
          }
	      obj.appointmentId = appointmentId
	      cdb.persistPatient(obj)
	      currentPatient = PatientVO(obj)
	          
	 }
	    
    fun removePatientattendsAppointment(appointmentId: String, patientId: String) {
		     var obj = getPatientByPK(patientId)
		     if (obj == null) {
	             obj = Patient.createByPKPatient(patientId)
	         }
		     obj.appointmentId = "Null"
		     cdb.persistPatient(obj)
	         currentPatient = PatientVO(obj)
		          
	}

    fun listPatient(): ArrayList<PatientVO> {
		  val patients: ArrayList<Patient> = Patient.PatientAllInstances
		  currentPatients.clear()
		  for (i in patients.indices) {
		       currentPatients.add(PatientVO(patients[i]))
		  }
			      
		 return currentPatients
	}
	
	fun listAllPatient(): ArrayList<Patient> {
		  val patients: ArrayList<Patient> = Patient.PatientAllInstances
		  return patients
	}

    fun stringListPatient(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
        for (x in currentPatients.indices) {
            res.add(currentPatients[x].toString())
        }
        return res
    }

    fun getPatientByPK(value: String): Patient? {
        return Patient.PatientIndex[value]
    }
    
    fun retrievePatient(value: String): Patient? {
            return getPatientByPK(value)
    }

    fun allPatientPatientIds(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
            for (x in currentPatients.indices) {
                res.add(currentPatients[x].patientId)
            }
        return res
    }
    fun setSelectedPatient(i: Int) {
        if (i < currentPatients.size) {
            currentPatient = currentPatients[i]
        }
    }

    fun getSelectedPatient(): PatientVO? {
        return currentPatient
    }

    fun persistPatient(x: Patient) {
        val vo = PatientVO(x)
        cdb.persistPatient(x)
        currentPatient = vo
    }
		
}
