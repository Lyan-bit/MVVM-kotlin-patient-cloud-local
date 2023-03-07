package com.example.patient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.patient.adapter.PagerAdapter
import com.example.patient.fragments.ListAppointmentFragment
import com.example.patient.fragments.ListPatientFragment
import com.example.patient.model.AppointmentVO
import com.example.patient.model.PatientVO
import com.example.patient.viewmodel.AppointmentViewModel
import com.example.patient.viewmodel.PatientViewModel
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), ListPatientFragment.OnListPatientFragmentInteractionListener, ListAppointmentFragment.OnListAppointmentFragmentInteractionListener  {
        
       	private lateinit var model: PatientViewModel
	private lateinit var modelAppointment: AppointmentViewModel
       	
       	override fun onCreate(savedInstanceState: Bundle?) {
       	      super.onCreate(savedInstanceState)
       	      setContentView(R.layout.activity_main)
       	
       	      val myPagerAdapter = PagerAdapter(this, supportFragmentManager)
       	      val viewpager: ViewPager = findViewById(R.id.view_pager)
       	      viewpager.adapter = myPagerAdapter
       	      val tabs: TabLayout = findViewById(R.id.tabs)
       	      tabs.setupWithViewPager(viewpager)
       	      model = PatientViewModel.getInstance(this)
			  modelAppointment = AppointmentViewModel.getInstance(this)
       	}
       	
       	override fun onListPatientFragmentInteraction(item: PatientVO) {
			model.setSelectedPatient(item)
       	}
       		 	       	
       	override fun onListAppointmentFragmentInteraction(item: AppointmentVO) {
			modelAppointment.setSelectedAppointment(item)
       	}
       		 	       	

    }
