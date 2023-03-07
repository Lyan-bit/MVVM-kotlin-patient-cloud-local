package com.example.patient.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.patient.R
import com.example.patient.fragments.ListPatientFragment
import com.example.patient.model.PatientVO

class PatientRecyclerViewAdapter (items: List<PatientVO>, listener: ListPatientFragment.OnListPatientFragmentInteractionListener)
    : RecyclerView.Adapter<PatientRecyclerViewAdapter.PatientViewHolder>() {

    private var mValues: List<PatientVO> = items
    private var mListener: ListPatientFragment.OnListPatientFragmentInteractionListener = listener
	
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerviewcrudpatient_item, parent, false)
        return PatientViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.crudPatientPatientIdView.text = " " + mValues[position].getPatientId() + " "
        holder.crudPatientNameView.text = " " + mValues[position].getName() + " "
        holder.crudPatientAppointmentIdView.text = " " + mValues[position].getAppointmentId() + " "

        holder.mView.setOnClickListener { mListener.onListPatientFragmentInteraction(holder.mItem) }
    }
    
    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class PatientViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var mView: View
        var crudPatientPatientIdView: TextView
        var crudPatientNameView: TextView
        var crudPatientAppointmentIdView: TextView
        lateinit var mItem: PatientVO

        init {
            mView = view
            crudPatientPatientIdView = view.findViewById(R.id.crudPatientPatientIdView)
            crudPatientNameView = view.findViewById(R.id.crudPatientNameView)
            crudPatientAppointmentIdView = view.findViewById(R.id.crudPatientAppointmentIdView)
        }

        override fun toString(): String {
            return super.toString() + " " + mItem
        }

    }
}
