package com.ust.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.dao.AdminDaoService;
import com.ust.model.AdminRole;
import com.ust.model.AssignLabTest;
import com.ust.model.AvailDoctor;
import com.ust.model.DoctorObservation;
import com.ust.model.DoctorStaff;
import com.ust.model.Medicine;
import com.ust.model.PatHistory;
import com.ust.model.Patient;
import com.ust.model.PrescDetail;
import com.ust.model.Prescription;
import com.ust.model.Staff;

@RestController
public class AdminController {

	@Autowired
	AdminDaoService dao;

	/*----------------------------------------ADMIN MODULE-----------------------------------------------*/

	// verify login
	@RequestMapping(value = "/api/admin/{username}/{userpassword}", method = RequestMethod.GET)
	@ResponseBody
	public AdminRole selectRole(@PathVariable("username") String username,
			@PathVariable("userpassword") String password) {
		return dao.selectRole(username, password);
	}

	// verify doc login
	@RequestMapping(value = "/api/doclogin/{username}/{userpassword}", method = RequestMethod.GET)
	@ResponseBody
	public AdminRole selectDocRole(@PathVariable("username") String username,
			@PathVariable("userpassword") String password) {
		return dao.selectDocRole(username, password);
	}

	// get doctor appointment
	@RequestMapping(value = "/api/doctor/drappoinment/{dId}", method = RequestMethod.GET)
	@ResponseBody
	public List viewTodaysDoctorAppointments(@PathVariable("dId") int dId) {
		System.out.println("inside controller" + dId);
		List listdoctoday = dao.getTodaysDoctorAppointment(dId);
		return listdoctoday;
	}

	// view staff list
	@RequestMapping(value = "/api/staff/{sName}", method = RequestMethod.GET)
	@ResponseBody
	public List getStaff(Model m, @PathVariable("sName") String sName) {
		List list;
		if (sName.equals("null")) {
			list = dao.getStaff();
		} else {
			list = dao.getStaffByName(sName);
		}

		return list;
	}

	// view staff list by id
	@RequestMapping(value = "/api/staffs/{sId}", method = RequestMethod.GET)
	@ResponseBody
	public Staff getStaff(Model m, @PathVariable("sId") int sId) {

		return dao.getStaffById(sId);

	}

	// view doctor list
	@RequestMapping(value = "/api/doctor/{sName}", method = RequestMethod.GET)
	@ResponseBody
	public List getDoctor(Model m, @PathVariable("sName") String sName) {
		List list;
		if (sName.equals("null")) {
			list = dao.getDoctor();
		} else {
			list = dao.getDocByName(sName);
		}

		return list;
	}

	// view doctor list by id
	@RequestMapping(value = "/api/doctors/{dId}", method = RequestMethod.GET)
	@ResponseBody
	public DoctorStaff getDoctor(Model m, @PathVariable("dId") int dId) {

		return dao.getDocById(dId);

	}

	// save staff
	@RequestMapping(value = "/api/insertstaff/{roleName}", method = RequestMethod.POST)
	public void saveStaff(@RequestBody Staff s,
			@PathVariable("roleName") String roleName) {

		dao.saveStaff(s, roleName);
	}

	// disable staff
	@RequestMapping(value = "/api/disablestaff/{sId}", method = RequestMethod.PUT)
	@ResponseBody
	public void disableStaff(@PathVariable("sId") int sId) {
		dao.disableStaff(sId);
	}

	// disable doctor
	@RequestMapping(value = "/api/disableDoctor/{dId}", method = RequestMethod.PUT)
	void doctorDisable(@PathVariable("dId") int dId) {
		dao.disableDoctor(dId);
	}

	// disable medicine
	@RequestMapping(value = "/api/disableMedicine/{medId}", method = RequestMethod.PUT)
	void medicineDisable(@PathVariable("medId") int medId) {
		dao.disableMedicine(medId);
	}

	// view medicine list
	@RequestMapping(value = "/api/medicine/{mName}", method = RequestMethod.GET)
	@ResponseBody
	public List getMedicine(Model m, @PathVariable("mName") String mName) {
		List list;
		if (mName.equals("null")) {
			list = dao.getMedicine();
		} else {
			list = dao.getMedByName(mName);
		}

		return list;
	}

	// view medicine list by id
	@RequestMapping(value = "/api/medicines/{mId}", method = RequestMethod.GET)
	@ResponseBody
	public List getMedicineById(Model m, @PathVariable("mId") String mId) {
		List list;

		list = dao.getMedById(mId);

		return list;
	}

	/*-----------------------------------------FRONT OFFICE MODULE-------------------------------------------*/

	// view patient list
	@RequestMapping(value = "/api/patients/{regName}", method = RequestMethod.GET)
	@ResponseBody
	public List getPatient(Model m, @PathVariable("regName") String regName) {
		List list;
		if (regName.equals("null")) {
			list = dao.getPatients();
		} else {
			list = dao.getPatientByName(regName);
		}

		return list;
	}

	// get patient info
	@RequestMapping(value = "/api/patient/{regId}", method = RequestMethod.GET)
	@ResponseBody
	public Patient getPatientById(@PathVariable("regId") int regId) {
		return dao.getPatientByRegId(regId);
	}

	// get available doctors
	@RequestMapping(value = "/api/doctoravail", method = RequestMethod.GET)
	@ResponseBody
	public List getAvailableDoctorByDay() {

		List list= dao.getAvailableDoctorByDay();
		return list;
	}

	/*--------------------------------DOCTOR MODULE------------------------------*/

	// get all tests
	@RequestMapping(value = "/api/labtests", method = RequestMethod.GET)
	@ResponseBody
	public List getLabTest(Model m) {
		List list = dao.getLabTest();
		return list;
	}

	// get all medicine
	@ResponseBody
	@RequestMapping(value = "api/doctor/medicine", method = RequestMethod.GET)
	public List getAllMedicines() {
		List medicineList = dao.getAllMedicines();
		return medicineList;
	}

	// add patient comments
	@RequestMapping(value = "/api/insertdocomments/{regId}/{dId}", method = RequestMethod.POST)
	public void insertDoctorComments(@RequestBody DoctorObservation obs,
			@PathVariable("regId") int regId, @PathVariable("dId") int dId) {

		dao.addPatientComments(obs, regId, dId);
	}

	// add lab test request
	@RequestMapping(value = "/api/assignLab/{labName}", method = RequestMethod.POST)
	public void insertLabTest(@RequestBody AssignLabTest doc_bean,
			@PathVariable("labName") String labName) {
		dao.addlabtestRequest(doc_bean, labName);
	}

	// get todays appointment

	@RequestMapping(value = "/api/doctor/appoinment", method = RequestMethod.GET)
	@ResponseBody
	public List viewTodaysAppointments() {
		List listtoday = dao.getTodaysAppointment();
		System.out.println("before ang" + listtoday);
		return listtoday;
	}

	// save medicine
	@RequestMapping(value = "/api/savemedicines", method = RequestMethod.POST)
	public void saveMedicine(@RequestBody Medicine a1) {
		dao.saveMed(a1);

	}

	// update medicine
	@ResponseBody
	@RequestMapping(value = "/api/updatemedicines/{medId}", method = RequestMethod.PUT)
	public void updateMedicine(@RequestBody Medicine a1,
			@PathVariable("medId") int medId) {

		dao.updateMed(a1, medId);
	}

	// save staff
	@ResponseBody
	@RequestMapping(value = "/api/savestaff/{roleName}", method = RequestMethod.POST)
	public void insertStaff(@RequestBody Staff s,
			@PathVariable("roleName") String roleName) throws ParseException {

		dao.saveStaff(s, roleName);

	}

	// update staff
	@ResponseBody
	@RequestMapping(value = "/api/updatestaff/{roleName}", method = RequestMethod.PUT)
	public void updateStaff(@RequestBody Staff s,
			@PathVariable("roleName") String roleName) throws ParseException {

		dao.updateStaff(s, roleName);

	}

	// save patient
	@RequestMapping(value = "/api/savepatient", method = RequestMethod.POST)
	public void savePatient(@RequestBody Patient pa) throws ParseException {

		dao.savePatient(pa);

	}

	// update patient
	@RequestMapping(value = "/api/updatepatient", method = RequestMethod.PUT)
	public void updatePatient(@RequestBody Patient pa) throws ParseException {

		dao.updatePatient(pa);

	}

	// save doctor
	@ResponseBody
	@RequestMapping(value = "/api/insertdoctor/{roleName}", method = RequestMethod.POST)
	public void insertDoctor(@RequestBody DoctorStaff ds,
			@PathVariable("roleName") String roleName) throws ParseException {
		dao.saveDoctor(ds, roleName);
	}

	// update doctor
	@ResponseBody
	@RequestMapping(value = "/api/updatedoctor/{roleName}", method = RequestMethod.PUT)
	public void updateDoctor(@RequestBody DoctorStaff ds,
			@PathVariable("roleName") String roleName) throws ParseException {
		int sId = ds.getsId();
		dao.updateDoctor(sId, ds, roleName);
	}

	// to add prescription

	@RequestMapping(value = "/api/addPrescription/{obserDate}", method = RequestMethod.POST)
	public void addPresc(@RequestBody Prescription prescription,
			@PathVariable("obserDate") String date) throws ParseException {
		System.out.println("entered");
		System.out.println(date);
		dao.addPrescription(prescription, date);
	}

	// to add prescriptionDetails

	@RequestMapping(value = "/api/addPrescDetail", method = RequestMethod.POST)
	public void addPresDetail(@RequestBody List<PrescDetail> prescDetail) {
		System.out.println("abhi");

		dao.addPrescDetail(prescDetail);
	}

	// to display prescription

	@ResponseBody
	@RequestMapping(value = "api/prescription", method = RequestMethod.GET)
	public List getAllPrescription() {
		List pres = dao.getPrescription();
		return pres;
	}

	// to display prescription Details
	@ResponseBody
	@RequestMapping(value = "api/prescDetail", method = RequestMethod.GET)
	public List getAllPrescDetail() {
		List pres = dao.getPrescriptionDetail();
		return pres;
	}

	//----------------------------- retrieve patient history
	
	//-----------retrieve Lab History
	@ResponseBody
	@RequestMapping(value = "/api/getLabHistory/{regId}", method = RequestMethod.GET)
	public List getLabHist(@PathVariable("regId") int regId) {

		List patHistory = dao.getlabHistory(regId);
		return patHistory;
	}
	
	//-----------retrieve Medicine History
		@ResponseBody
		@RequestMapping(value = "/api/getMedicineHistory/{regId}", method = RequestMethod.GET)
		public List getMedHist(@PathVariable("regId") int regId) {

			List patHistory = dao.getMedicineHistory(regId);
			return patHistory;
		}
		

		//-----------retrieve Observation History
			@ResponseBody
			@RequestMapping(value = "/api/getObservationHistory/{regId}", method = RequestMethod.GET)
			public List getObsHist(@PathVariable("regId") int regId) {

				List patHistory = dao.getObservationHistory(regId);
				return patHistory;
			}

}	
