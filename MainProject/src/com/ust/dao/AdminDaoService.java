package com.ust.dao;

import java.util.List;

import com.ust.model.AdminRole;
import com.ust.model.AssignLabTest;
import com.ust.model.DoctorAppointment;
import com.ust.model.DoctorObservation;
import com.ust.model.DoctorStaff;
import com.ust.model.LabTest;
import com.ust.model.Medicine;
import com.ust.model.PatHistory;
import com.ust.model.Patient;
import com.ust.model.PrescDetail;
import com.ust.model.Prescription;
import com.ust.model.Staff;

public interface AdminDaoService {

	/*---------------------------------ADMIN MODULE-----------------------------*/
	// verify login
	public abstract AdminRole selectRole(String username, String password);

	// verify doctor login
	public abstract AdminRole selectDocRole(String username, String password);

	// view staff list
	public abstract List<Staff> getStaff();

	// view staff by name
	public abstract List<Staff> getStaffByName(String sName);

	public abstract Staff getStaffById(int sId);

	// view all doctors
	public abstract List<DoctorStaff> getDoctor();

	// view doctor by name
	public abstract List<DoctorStaff> getDocByName(String sName);

	// view doctor by id
	public abstract DoctorStaff getDocById(int dId);

	public abstract AdminRole getRoleName(String roleName);

	// disable staff
	public abstract int disableStaff(int sId);

	// disable doctor
	public abstract int disableDoctor(int dId);

	// disable medicine
	public abstract int disableMedicine(int medId);

	// view medicine list
	public abstract List<Medicine> getMedicine();

	// view medicine by name
	public abstract List<Medicine> getMedByName(String medName);

	public abstract List<Medicine> getMedById(String medId);

	// save medicine
	public abstract int saveMed(Medicine a1);

	public abstract int updateMed(Medicine a1, int medId);

	// save staff
	public abstract int saveStaff(Staff staff, String roleName);

	// save patient
	public abstract int savePatient(Patient pa);

	// save doctor
	public abstract int saveDoctor(DoctorStaff ds, String roleName);

	// view patient list
	public abstract List<Patient> getPatients();

	// view patient by name
	public abstract List<Patient> getPatientByName(String regName);

	// get patient info
	public abstract Patient getPatientByRegId(int regId);

	// get available doctors
	public abstract List<Staff> getAvailableDoctorByDay();

	// get all test
	public abstract List<LabTest> getLabTest();

	// get all medicine
	public abstract List<Medicine> getAllMedicines();

	// add patient comments
	public abstract int addPatientComments(DoctorObservation obs, int regId,
			int dId);

	// add lab test request
	public abstract int doc_getLabId(String labName);

	public abstract int addlabtestRequest(AssignLabTest doc_bean, String labName);

	// get doctor appointment
	public abstract List<DoctorAppointment> getTodaysDoctorAppointment(int dId);

	// get todays appointment
	public abstract List<DoctorAppointment> getTodaysAppointment();

	public abstract long getDocId(String date, int regId);

	public abstract int addPrescription(Prescription prescription, String date);

	public abstract int addPrescDetail(List<PrescDetail> prescDetail);

	public abstract List<Prescription> getPrescription();

	public abstract List<PrescDetail> getPrescriptionDetail();

	public abstract int updatePatient(Patient pa);

	public abstract int updateDoctor(int sId, DoctorStaff doc, String roleName);

	// update staff
	public abstract int updateStaff(Staff st, String roleName);

	public abstract List<PatHistory> getPatHistory(int regId);

	// get Lab History
	public abstract List<PatHistory> getlabHistory(int regId);

	// get Medicine History
	public abstract List<PatHistory> getMedicineHistory(int regId);

	// get Obervation History
	public abstract List<PatHistory> getObservationHistory(int regId);

}