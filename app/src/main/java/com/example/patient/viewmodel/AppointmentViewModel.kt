package com.example.patient.viewmodel

import android.content.Context
import com.example.patient.database.DB
import com.example.patient.database.FileAccessor
import com.example.patient.model.Appointment
import com.example.patient.model.AppointmentVO
import java.util.ArrayList


class AppointmentViewModel private constructor(context: Context) {

    private var db: DB
    private var fileSystem: FileAccessor

    private var currentAppointment: AppointmentVO? = null
	private var currentAppointments: ArrayList<AppointmentVO> = ArrayList()

    init {
        db = DB(context, null)
        fileSystem = FileAccessor(context)
	}

    companion object {
        private var instance: AppointmentViewModel? = null
        fun getInstance(context: Context): AppointmentViewModel {
            return instance ?: AppointmentViewModel(context)
        }
    }

    fun createAppointment(x: AppointmentVO) {
          db.createAppointment(x)
          currentAppointment = x
	}
		    
    fun setSelectedAppointment(x: AppointmentVO) {
	      currentAppointment = x
	}
	    
    fun editAppointment(x: AppointmentVO) {
        db.editAppointment(x)
          currentAppointment = x
	}
		
    fun deleteAppointment(id: String) {
          db.deleteAppointment(id)
          currentAppointment = null
	}

	fun listAppointment(): ArrayList<AppointmentVO> {
        currentAppointments = db.listAppointment()
		
        return currentAppointments
	}

	fun listAllAppointment(): ArrayList<Appointment> {
		currentAppointments = db.listAppointment()
		var res = ArrayList<Appointment>()
			for (x in currentAppointments.indices) {
					val vo: AppointmentVO = currentAppointments[x]
				    val itemx = Appointment.createByPKAppointment(vo.getAppointmentId())
	            itemx.appointmentId = vo.getAppointmentId()
            itemx.code = vo.getCode()
			res.add(itemx)
		}
		return res
	}

    fun stringListAppointment(): ArrayList<String> {
        currentAppointments = db.listAppointment()
        val res: ArrayList<String> = ArrayList()
        for (x in currentAppointments.indices) {
            res.add(currentAppointments[x].toString())
        }
        return res
    }

    fun getAppointmentByPK(value: String): Appointment? {
        val res: ArrayList<AppointmentVO> = db.searchByAppointmentappointmentId(value)
	        return if (res.isEmpty()) {
	            null
	        } else {
	            val vo: AppointmentVO = res[0]
	            val itemx = Appointment.createByPKAppointment(value)
            itemx.appointmentId = vo.getAppointmentId()
            itemx.code = vo.getCode()
	            itemx
	        }
    }
    
    fun retrieveAppointment(value: String): Appointment? {
        return getAppointmentByPK(value)
    }

    fun allAppointmentAppointmentIds(): ArrayList<String> {
        currentAppointments = db.listAppointment()
        val res: ArrayList<String> = ArrayList()
            for (appointment in currentAppointments.indices) {
                res.add(currentAppointments[appointment].getAppointmentId())
            }
        return res
    }

    fun setSelectedAppointment(i: Int) {
        if (i < currentAppointments.size) {
            currentAppointment = currentAppointments[i]
        }
    }

    fun getSelectedAppointment(): AppointmentVO? {
        return currentAppointment
    }

    fun persistAppointment(x: Appointment) {
        val vo = AppointmentVO(x)
        db.editAppointment(vo)
        currentAppointment = vo
    }
	
    fun searchByAppointmentappointmentId(appointmentIdx: String): ArrayList<Appointment> {
        currentAppointments = db.searchByAppointmentappointmentId(appointmentIdx)
		var res = ArrayList<Appointment>()
			for (x in currentAppointments.indices) {
					val vo: AppointmentVO = currentAppointments[x]
				    val itemx = Appointment.createByPKAppointment(vo.getAppointmentId())
	            itemx.appointmentId = vo.getAppointmentId()
            itemx.code = vo.getCode()
			res.add(itemx)
		}
		return res
	}
	
    fun searchByAppointmentcode(codex: String): ArrayList<Appointment> {
        currentAppointments = db.searchByAppointmentcode(codex)
		var res = ArrayList<Appointment>()
			for (x in currentAppointments.indices) {
					val vo: AppointmentVO = currentAppointments[x]
				    val itemx = Appointment.createByPKAppointment(vo.getAppointmentId())
	            itemx.appointmentId = vo.getAppointmentId()
            itemx.code = vo.getCode()
			res.add(itemx)
		}
		return res
	}

}
