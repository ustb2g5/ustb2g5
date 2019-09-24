package com.ust.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ust.model.AdminRole;
import com.ust.model.AssignLabTest;
import com.ust.model.Consultation;
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

public class AdminDao implements AdminDaoService {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	/*---------------------------------ADMIN MODULE-----------------------------*/
	// verify login
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#selectRole(java.lang.String, java.lang.String)
	 */
	@Override
	public AdminRole selectRole(String username, String password) {
		String sql = "select roleId,roleName from cm_roleTable where roleId=(select roleId from"
				+ " cm_staffTable where username='"
				+ username
				+ "' and password='" + password + "')";
		return template.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<AdminRole>(AdminRole.class));
	}

	// verify doctor login
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#selectDocRole(java.lang.String, java.lang.String)
	 */
	@Override
	public AdminRole selectDocRole(String username, String password) {
		String sql = " select cd.dId from cm_doctorTable cd join cm_staffTable cs on cd.sId = cs.sId where username='"
				+ username + "' and password='" + password + "'";

		return template.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<AdminRole>(AdminRole.class));
	}

	// view staff list
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getStaff()
	 */
	@Override
	public List<Staff> getStaff() {
		return template
				.query("select sId,sName,roleName,sPhNo,sEmail,sGender,sAddr,sExp,isActive,createdDate,dob from cm_staffTable stf join cm_roleTable role on(stf.roleId=role.roleId) where isActive='yes' and (role.roleName='admin' or role.roleName='lab' or role.roleName='front' or role.roleName='pharmacy')",
						new RowMapper<Staff>() {
							public Staff mapRow(ResultSet rs, int row)
									throws SQLException {
								Staff s = new Staff();
								s.setsId(rs.getInt(1));
								s.setsName(rs.getString(2));
								s.setRoleName(rs.getString(3));
								s.setsPhNo(rs.getString(4));
								s.setsEmail(rs.getString(5));
								s.setsGender(rs.getString(6));
								s.setsAddr(rs.getString(7));
								s.setsExp(rs.getString(8));
								s.setIsActive(rs.getString(9));
								s.setCreatedDate(rs.getDate(10));
								s.setDOB(rs.getDate(11));
								return s;
							}
						});
	}

	// view staff by name
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getStaffByName(java.lang.String)
	 */
	@Override
	public List<Staff> getStaffByName(String sName) {
		return template
				.query("select sId,sName,roleName,sPhNo,sEmail,sGender,sAddr,sExp,isActive,createdDate,dob from cm_staffTable stf join cm_roleTable role on(stf.roleId=role.roleId)"
						+ "where sName='" + sName + "'",
						new RowMapper<Staff>() {
							public Staff mapRow(ResultSet rs, int row)
									throws SQLException {
								Staff s = new Staff();
								s.setsId(rs.getInt(1));
								s.setsName(rs.getString(2));
								s.setRoleName(rs.getString(3));
								s.setsPhNo(rs.getString(4));
								s.setsEmail(rs.getString(5));
								s.setsGender(rs.getString(6));
								s.setsAddr(rs.getString(7));
								s.setsExp(rs.getString(8));
								s.setIsActive(rs.getString(9));
								s.setCreatedDate(rs.getDate(10));
								s.setDOB(rs.getDate(11));

								return s;
							}
						});
	}

	// view staff by sid

	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getStaffById(int)
	 */
	@Override
	public Staff getStaffById(int sId) {

		String sql = "select sId,sName,roleName,sPhNo,sEmail,sGender,sAddr,sExp,isActive,createdDate,dob,username,password from cm_staffTable stf join cm_roleTable role on(stf.roleId=role.roleId)"
				+ "where sId=" + sId;
		return template.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<Staff>(Staff.class));

	}

	// view all doctors
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getDoctor()
	 */
	@Override
	public List<DoctorStaff> getDoctor() {
		return template
				.query("select stf.sName,stf.sGender,stf.sAddr,stf.sExp,stf.sPhNo,stf.sEmail,stf.isActive"
						+ ",stf.createdDate,stf.dob,doc.dSpec,doc.dId,doc.dQualification,cs.sunday,cs.monday,cs.tuesday,cs.wednesday,cs.thursday,cs.friday,cs.saturday,stf.sId,stf.username,stf.password,doc.consultFee from cm_doctorTable doc join "
						+ "cm_staffTable stf on(doc.sId=stf.sId) join cm_consultationdays cs on(doc.dId=cs.dId) where stf.isActive='yes'",
						new RowMapper<DoctorStaff>() {
							public DoctorStaff mapRow(ResultSet rs, int row)
									throws SQLException {
								DoctorStaff doc = new DoctorStaff();

								doc.setsName(rs.getString(1));
								doc.setsGender(rs.getString(2));
								doc.setsAddr(rs.getString(3));
								doc.setsExp(rs.getString(4));
								doc.setsPhNo(rs.getString(5));
								doc.setsEmail(rs.getString(6));
								doc.setIsActive(rs.getString(7));
								doc.setCreatedDate(rs.getDate(8));
								doc.setDOB(rs.getDate(9));
								doc.setdSpec(rs.getString(10));
								doc.setdId(rs.getInt(11));
								doc.setdQualification(rs.getString(12));
								doc.setSunday(rs.getString(13));
								doc.setMonday(rs.getString(14));
								doc.setTuesday(rs.getString(15));
								doc.setWednesday(rs.getString(16));
								doc.setThursday(rs.getString(17));
								doc.setFriday(rs.getString(18));
								doc.setSaturday(rs.getString(19));
								doc.setsId(rs.getInt(20));
								doc.setUsername(rs.getString(21));
								doc.setPassword(rs.getString(22));
								doc.setConsultFee(rs.getInt(23));
								return doc;

							}
						});
	}

	// view doctor by name
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getDocByName(java.lang.String)
	 */
	@Override
	public List<DoctorStaff> getDocByName(String sName) {
		return template
				.query("select sName,sGender,sAddr,sExp,sPhNo,sEmail,isActive"
						+ ",createdDate,dob,dSpec,dId,dQualification from cm_doctorTable doc join "
						+ "cm_staffTable stf on(doc.sId=stf.sId) where sName='"
						+ sName + "'", new RowMapper<DoctorStaff>() {
					public DoctorStaff mapRow(ResultSet rs, int row)
							throws SQLException {
						DoctorStaff doc = new DoctorStaff();
						doc.setsName(rs.getString(1));
						doc.setsGender(rs.getString(2));
						doc.setsAddr(rs.getString(3));
						doc.setsExp(rs.getString(4));
						doc.setsPhNo(rs.getString(5));
						doc.setsEmail(rs.getString(6));
						doc.setIsActive(rs.getString(7));
						doc.setCreatedDate(rs.getDate(8));
						doc.setDOB(rs.getDate(9));
						doc.setdSpec(rs.getString(10));
						doc.setdId(rs.getInt(11));
						doc.setdQualification(rs.getString(12));
						return doc;

					}
				});
	}

	// view doctor by id
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getDocById(int)
	 */
	@Override
	public DoctorStaff getDocById(int dId) {

		String sql = "select stf.sName,stf.sGender,stf.sAddr,stf.sExp,stf.sPhNo,stf.sEmail,stf.isActive"
				+ ",stf.createdDate,stf.dob,doc.dSpec,doc.dQualification,doc.dId,cs.consultDayId,cs.sunday,cs.monday,cs.tuesday,cs.wednesday,cs.thursday,cs.friday,cs.saturday,stf.sId,stf.username,stf.password,doc.consultFee from cm_doctorTable doc join "
				+ "cm_staffTable stf on(doc.sId=stf.sId) join cm_consultationdays cs on(doc.dId=cs.dId) where doc.did="
				+ dId;
		return template.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<DoctorStaff>(DoctorStaff.class));

	}

	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getRoleName(java.lang.String)
	 */
	@Override
	public AdminRole getRoleName(String roleName) {
		String sql = "select roleId from cm_roleTable where roleName='"
				+ roleName + "'";

		return template.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<AdminRole>(AdminRole.class));

	}

	// disable staff
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#disableStaff(int)
	 */
	@Override
	public int disableStaff(int sId) {

		String sql = "update cm_staffTable set isActive='1' where sId=" + sId
				+ "";

		return template.update(sql);
	}

	// disable doctor
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#disableDoctor(int)
	 */
	@Override
	public int disableDoctor(int dId) {
		String sql = "update cm_StaffTable set isActive='1' where sId=(select sId"
				+ " from cm_DoctorTable where dId=?)";
		return template.update(sql, new Object[] { dId });
	}

	// disable medicine
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#disableMedicine(int)
	 */
	@Override
	public int disableMedicine(int medId) {

		String sql = "update cm_medicineTable set isActive=1 where medId="
				+ medId + "";

		return template.update(sql);
	}

	// view medicine list
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getMedicine()
	 */
	@Override
	public List<Medicine> getMedicine() {
		return template
				.query("select medId,medName,medPrice,manufacturer,isActive,createdDate from cm_medicineTable where isActive=0",
						new RowMapper<Medicine>() {
							public Medicine mapRow(ResultSet rs, int row)
									throws SQLException {
								Medicine a1 = new Medicine();

								a1.setMedId(rs.getInt(1));
								a1.setMedName(rs.getString(2));
								a1.setMedPrice(rs.getInt(3));
								a1.setManufacturer(rs.getString(4));
								a1.setIsActiveM(rs.getInt(5));
								a1.setCreatedDateM(rs.getDate(6));
								return a1;

							}
						});
	}

	// view medicine by name
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getMedByName(java.lang.String)
	 */
	@Override
	public List<Medicine> getMedByName(String medName) {
		return template
				.query("select medId,medName,medPrice,manufacturer,isActive,createdDate from cm_medicineTable "
						+ " where medName='" + medName + "'",
						new RowMapper<Medicine>() {
							public Medicine mapRow(ResultSet rs, int row)
									throws SQLException {
								Medicine a1 = new Medicine();

								a1.setMedId(rs.getInt(1));
								a1.setMedName(rs.getString(2));
								a1.setMedPrice(rs.getInt(3));
								a1.setManufacturer(rs.getString(4));
								a1.setIsActiveM(rs.getInt(5));
								a1.setCreatedDateM(rs.getDate(6));
								return a1;

							}
						});
	}

	// view medicine by id

	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getMedById(java.lang.String)
	 */
	@Override
	public List<Medicine> getMedById(String medId) {
		return template
				.query("select medId,medName,medPrice,manufacturer,isActive,createdDate from cm_medicineTable "
						+ " where medId=" + medId + "",
						new RowMapper<Medicine>() {
							public Medicine mapRow(ResultSet rs, int row)
									throws SQLException {
								Medicine m1 = new Medicine();

								m1.setMedId(rs.getInt(1));
								m1.setMedName(rs.getString(2));
								m1.setMedPrice(rs.getInt(3));
								m1.setManufacturer(rs.getString(4));
								m1.setIsActiveM(rs.getInt(5));
								m1.setCreatedDateM(rs.getDate(6));
								return m1;

							}
						});
	}

	// save medicine
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#saveMed(com.ust.model.Medicine)
	 */
	@Override
	public int saveMed(Medicine a1) {

		String sql = "insert into cm_medicineTable(medName,medPrice,manufacturer,createdDate,isActive) values"

				+ "('"
				+ a1.getMedName()
				+ "',"
				+ a1.getMedPrice()
				+ ",'"

				+ a1.getManufacturer()
				+ "',TO_DATE('"
				+ java.time.LocalDate.now() + "','YYYY-MM-DD')" + "," + 0 + ")";

		return template.update(sql);

	}

	// update medicine

	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#updateMed(com.ust.model.Medicine, int)
	 */
	@Override
	public int updateMed(Medicine a1, int medId) {

		String sql = "update cm_medicineTable set medName='" + a1.getMedName()

		+ "',medPrice=" + a1.getMedPrice() + ",isActive=" + 0
				+ ",manufacturer='" + a1.getManufacturer()

				+ "',createdDate= TO_DATE('" + java.time.LocalDate.now()
				+ "','YYYY-MM-DD')  where medId=" + medId;

		return template.update(sql);

	}

	// save staff
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#saveStaff(com.ust.model.Staff, java.lang.String)
	 */
	@Override
	public int saveStaff(Staff staff, String roleName) {

		AdminRole ad = getRoleName(roleName);

		SimpleDateFormat ft = new SimpleDateFormat("YYYY-MM-dd");
		String sqlDate = ft.format(staff.getDOB());

		String yes = "yes";
		String sql = "insert into cm_staffTable(roleId,sName,DOB,sGender,sAddr,"
				+ "sExp,sPhNo,sEmail,username,password,isActive,createdDate) values "
				+ "("
				+ ad.getRoleId()
				+ ",'"
				+ staff.getsName()
				+ "',"
				+ "TO_DATE('"
				+ sqlDate
				+ "','YYYY-MM-dd')"
				+ ",'"
				+ staff.getsGender()
				+ "','"
				+ staff.getsAddr()
				+ "','"
				+ staff.getsExp()
				+ "','"
				+ staff.getsPhNo()
				+ "','"
				+ staff.getsEmail()
				+ "','"
				+ staff.getUsername()
				+ "','"
				+ staff.getPassword()
				+ "','"
				+ yes
				+ "',"
				+ "TO_DATE('"
				+ java.time.LocalDate.now()
				+ "','YYYY-MM-DD')" + ")";

		return template.update(sql);
	}

	// save patient
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#savePatient(com.ust.model.Patient)
	 */
	@Override
	public int savePatient(Patient pa) {

		SimpleDateFormat ft = new SimpleDateFormat("YYYY-MM-dd");
		String sqlDate = ft.format(pa.getDOB());

		String sql = "insert into cm_patientTable(pFName,pLName,pGender,DOB,pAddr,pPhNo,pBloodGrp,createdDate) values('"
				+ pa.getpFName()
				+ "','"
				+ pa.getpLName()
				+ "','"
				+ pa.getpGender()
				+ "',"
				+ "TO_DATE('"
				+ sqlDate
				+ "','YYYY-MM-dd')"
				+ ",'"
				+ pa.getpAddr()
				+ "','"
				+ pa.getpPhNo()
				+ "','"
				+ pa.getpBloodGrp()
				+ "',"
				+ "TO_DATE('"
				+ java.time.LocalDate.now()
				+ "','YYYY-MM-DD')"
				+ ")";
		return template.update(sql);

	}

	// save doctor
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#saveDoctor(com.ust.model.DoctorStaff, java.lang.String)
	 */
	@Override
	public int saveDoctor(DoctorStaff ds, String roleName) {
		java.util.Date date = new java.util.Date();
		long t = date.getTime();
		java.sql.Date sqlDate = new java.sql.Date(t);

		AdminRole ad = getRoleName(roleName);

		String yes = "yes";
		String sql = "insert into cm_staffTable(roleId ,sName ,DOB ,sGender,sAddr,sExp,sPhNo,"
				+ "sEmail,username,password,isActive ,createdDate)values("
				+ ad.getRoleId()
				+ ",'"
				+ ds.getsName()
				+ "',"
				+ "TO_DATE('"
				+ sqlDate
				+ "','YYYY-MM-dd')"
				+ ",'"
				+ ds.getsGender()
				+ "','"
				+ ds.getsAddr()
				+ "','"
				+ ds.getsExp()
				+ "','"
				+ ds.getsPhNo()
				+ "','"
				+ ds.getsEmail()
				+ "','"
				+ ds.getUsername()
				+ "','"
				+ ds.getPassword()
				+ "','"
				+ yes
				+ "',"
				+ "TO_DATE('"
				+ java.time.LocalDate.now() + "','YYYY-MM-DD')" + ")";
		template.update(sql);

		Integer maxId = getSequence();

		String sql1 = "insert into cm_doctorTable(sId,dSpec,dQualification,consultFee)values("
				+ maxId
				+ ",'"
				+ ds.getdSpec()
				+ "','"
				+ ds.getdQualification()
				+ "'," + ds.getConsultFee() + ")";

		template.update(sql1);

		Integer maxDid = getDocSequence();
		String sql2 = "insert into cm_consultationDays(dId,sunday,monday,tuesday,wednesday,thursday,friday,saturday) values ("
				+ maxDid
				+ ",'"
				+ ds.getSunday()
				+ "','"
				+ ds.getMonday()
				+ "','"
				+ ds.getTuesday()
				+ "','"
				+ ds.getWednesday()
				+ "','"
				+ ds.getThursday()
				+ "','"
				+ ds.getFriday()
				+ "','"
				+ ds.getSaturday() + "')";

		return template.update(sql2);

	}

	private Integer getSequence() {
		Integer seq;
		String sql = "select MAX(sId)from cm_staffTable";
		seq = template.queryForObject(sql, new Object[] {}, Integer.class);
		return seq;
	}

	private Integer getDocSequence() {
		Integer seq;
		String sql = "select MAX(dId)from cm_doctorTable";
		seq = template.queryForObject(sql, new Object[] {}, Integer.class);
		return seq;
	}

	/*---------------------------------FRONT OFFICE MODULE----------------------*/

	// view patient list
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getPatients()
	 */
	@Override
	public List<Patient> getPatients() {

		return template
				.query("select regId,pFName,pLName,pGender,DOB,pAddr,pPhNo,pBloodGrp,createdDate from cm_patientTable",
						new RowMapper<Patient>() {
							public Patient mapRow(ResultSet rs, int row)
									throws SQLException {
								Patient pa = new Patient();
								pa.setRegId(rs.getInt(1));
								pa.setpFName(rs.getString(2));
								pa.setpLName(rs.getString(3));
								pa.setpGender(rs.getString(4));
								pa.setDOB(rs.getDate(5));
								pa.setpAddr(rs.getString(6));
								pa.setpPhNo(rs.getString(7));
								pa.setpBloodGrp(rs.getString(8));
								pa.setCreatedDate(rs.getDate(9));
								return pa;

							}
						});
	}

	// view patient by name
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getPatientByName(java.lang.String)
	 */
	@Override
	public List<Patient> getPatientByName(String regName) {
		return template.query(
				"select regId,pFName,pLName,pGender,DOB,pAddr,pPhNo,pBloodGrp,"
						+ " createdDate from cm_patientTable where pFName='"
						+ regName + "'", new RowMapper<Patient>() {
					@Override
					public Patient mapRow(ResultSet rs, int row)
							throws SQLException {
						Patient pa = new Patient();
						pa.setRegId(rs.getInt(1));
						pa.setpFName(rs.getString(2));
						pa.setpLName(rs.getString(3));
						pa.setpGender(rs.getString(4));
						pa.setDOB(rs.getDate(5));
						pa.setpAddr(rs.getString(6));
						pa.setpPhNo(rs.getString(7));
						pa.setpBloodGrp(rs.getString(8));
						pa.setCreatedDate(rs.getDate(9));
						return pa;

					}
				});

	}

	// get patient info
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getPatientByRegId(int)
	 */
	@Override
	public Patient getPatientByRegId(int regId) {
		String sql = "select regId,pFName,pLName,pGender,DOB,pAddr,pPhNo,pBloodGrp,createdDate from cm_patientTable where regId=?";
		return template.queryForObject(sql, new Object[] { regId },
				new BeanPropertyRowMapper<Patient>(Patient.class));

	}

	// get available doctors
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getAvailableDoctorByDay()
	 */
	@Override
	public List<Staff> getAvailableDoctorByDay() {

		LocalDate date = LocalDate.now();
		String day = date.getDayOfWeek().name();
		System.out.println(day);

		return template
				.query("select cm_stafftable.sName,cm_doctorTable.dSpec,cm_doctorTable.dId from cm_staffTable "
						+ "join cm_doctorTable on(cm_staffTable.sId=cm_doctorTable.sId) "
						+ "join cm_consultationDays on(cm_doctorTable.dId=cm_consultationDays.dId)"
						+ " where " + day + "='1'", new RowMapper<Staff>() {
					public Staff mapRow(ResultSet rs, int row)
							throws SQLException {
						Staff staff = new Staff();
						staff.setdId(rs.getInt(3));
						staff.setsName(rs.getString(1));
						staff.setdSpec(rs.getString(2));

						return staff;

					}
				});

	}

	// return template.queryForObject(sql, new Object[] {},
	// new BeanPropertyRowMapper<Staff>(Staff.class));

	/*---------------------------------DOCTOR MODULE-------------------------------------*/

	// get all test
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getLabTest()
	 */
	@Override
	public List<LabTest> getLabTest() {
		return template.query(
				"select labId,lName,lFee,description from cm_labTestTable",
				new RowMapper<LabTest>() {
					public LabTest mapRow(ResultSet rs, int row)
							throws SQLException {
						LabTest lab = new LabTest();
						lab.setLabId(rs.getInt(1));
						lab.setlName(rs.getString(2));
						lab.setlFee(rs.getInt(3));
						lab.setDescription(rs.getString(4));
						return lab;

					}
				});
	}

	// get all medicine
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getAllMedicines()
	 */
	@Override
	public List<Medicine> getAllMedicines() {
		return template.query(
				"Select medId,medName,medPrice,isActive,manufacturer,"
						+ "createdDate from cm_medicineTable",
				new RowMapper<Medicine>() {
					public Medicine mapRow(ResultSet rs, int row)
							throws SQLException {
						Medicine doc = new Medicine();
						doc.setMedId(rs.getInt(1));
						doc.setMedName(rs.getString(2));
						doc.setMedPrice(rs.getDouble(3));
						doc.setIsActiveM(rs.getInt(4));
						doc.setManufacturer(rs.getString(5));
						doc.setCreatedDateM(rs.getDate(6));
						return doc;
					}
				});

	}

	// add patient comments
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#addPatientComments(com.ust.model.DoctorObservation, int, int)
	 */
	@Override
	public int addPatientComments(DoctorObservation obs, int regId, int dId) {
		String sql = "insert into cm_doctorObsTable(obserDate,obsNotes,regId,dId) values('"
				+ obs.getObserDate()
				+ "','"
				+ obs.getObsNotes()
				+ "',"
				+ regId
				+ "," + dId + ")";
		return template.update(sql);
	}

	// add lab test request
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#doc_getLabId(java.lang.String)
	 */
	@Override
	public int doc_getLabId(String labName) {
		String sql = "select labId from cm_labTestTable where lName='"
				+ labName + "'";
		template.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<LabTest>(LabTest.class));
		LabTest lt = new LabTest();
		int labId = lt.getLabId();
		System.out.println(labId);
		return labId;
	}

	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#addlabtestRequest(com.ust.model.AssignLabTest, java.lang.String)
	 */
	@Override
	public int addlabtestRequest(AssignLabTest doc_bean, String labName) {
		Integer labId = doc_getLabId(labName);
		String sql = "insert into cm_assignLabTable(regId,dId,labId,assigLabDate,sampleStatus,testStatus)"
				+ "values("
				+ doc_bean.getRegId()
				+ ","
				+ doc_bean.getdId()
				+ ","
				+ labId
				+ ",TO_DATE('"
				+ java.time.LocalDate.now()
				+ "','YYYY-MM-DD'),'Yes','Yes')";

		return template.update(sql);
	}

	// get doctor appointment
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getTodaysDoctorAppointment(int)
	 */
	@Override
	public List<DoctorAppointment> getTodaysDoctorAppointment(int dId) {
		System.out.println("inside admin dao" + dId);
		LocalDate date = LocalDate.now();

		LocalDate dt = LocalDate.parse((date.toString()));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-YY");
		System.out.println(formatter.format(dt).toUpperCase());

		// System.out.println(new SimpleDateFormat("dd-MMM-yyyy").format(date));
		return template
				.query("select ca.appId,ca.regId,cp.pFName,cp.pLName,consultStatus from cm_appoinmentTable ca"
						+ " join cm_patientTable cp on ca.regId=cp.regId join cm_doctorTable cd on "
						+ "ca.dId=cd.dId join cm_staffTable cs on cs.sId=cd.sId where cd.dId="
						+ dId
						+ " and dateofApp='"
						+ formatter.format(dt).toUpperCase() + "'",
						new RowMapper<DoctorAppointment>() {
							public DoctorAppointment mapRow(ResultSet rs,
									int row) throws SQLException {
								DoctorAppointment dj = new DoctorAppointment();
								dj.setAppId(rs.getInt(1));
								dj.setRegId(rs.getInt(2));
								dj.setpFName(rs.getString(3));
								dj.setpLName(rs.getString(4));
								dj.setConsultStatus(rs.getString(5));
								return dj;
							}

						});
	}

	// get todays appointment
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getTodaysAppointment()
	 */
	@Override
	public List<DoctorAppointment> getTodaysAppointment() {
		LocalDate date = LocalDate.now();

		LocalDate dt = LocalDate.parse((date.toString()));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-YY");
		System.out.println(formatter.format(dt).toUpperCase());
		return template
				.query("select ca.appId,ca.regId,cp.pFName,cp.pLName,cs.sName, consultStatus from cm_appoinmentTable ca"
						+ " join cm_patientTable cp on ca.regId=cp.regId join cm_doctorTable cd on "
						+ "ca.dId=cd.dId join cm_staffTable cs on cd.sid=cs.sid where ca.dateOfApp='"
						+ formatter.format(dt).toUpperCase() + "'",
						new RowMapper<DoctorAppointment>() {
							public DoctorAppointment mapRow(ResultSet rs,
									int row) throws SQLException {
								DoctorAppointment dj = new DoctorAppointment();
								dj.setAppId(rs.getInt(1));
								dj.setRegId(rs.getInt(2));
								dj.setpFName(rs.getString(3));
								dj.setpLName(rs.getString(4));
								dj.setsName(rs.getString(5));
								dj.setConsultStatus(rs.getString(6));
								return dj;
							}

						});

	}

	// add prescription

	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getDocId(java.lang.String, int)
	 */
	@Override
	public long getDocId(String date, int regId) {
		System.out.println("Abhi");
		String sql = "select did from cm_doctorobstable where regId=" + regId
				+ " AND obserDate='" + date + "'";
		Prescription p = template.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<Prescription>(Prescription.class));

		long docId = p.getdId();
		return docId;

	}

	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#addPrescription(com.ust.model.Prescription, java.lang.String)
	 */
	@Override
	public int addPrescription(Prescription prescription, String date) {
		System.out.println(prescription.getRegId());
		long docId = getDocId(date, prescription.getRegId());
		System.out.println(docId);
		System.out.println("diya2");

		String sql = "";
		int result = 0;

		sql = "insert into cm_prescriptiontable (dId,regId) values(" + docId
				+ "," + prescription.getRegId() + ")";
		System.out.println(sql);
		result = template.update(sql);
		return result;

	}

	// add to prescription detail

	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#addPrescDetail(java.util.List)
	 */
	@Override
	public int addPrescDetail(List<PrescDetail> prescDetail) {
		System.out.println("entered");

		java.util.Date date = new java.util.Date();
		long t = date.getTime();
		java.sql.Date sqlDate = new java.sql.Date(t);

		String sql = "";
		int result = 0;

		for (PrescDetail presc : prescDetail) {
			sql = "insert into cm_prescDetailTable(prescId,medId,medFreq,medDays,prescDate,pharmacyStatus) values("
					+ presc.getPrescId()
					+ ","
					+ presc.getMedId()
					+ ",'"
					+ presc.getMedFreq()
					+ "',"
					+ presc.getMedDays()
					+ ",TO_DATE('"
					+ sqlDate
					+ "','YYYY-MM-dd') ,'"
					+ presc.getPharmacyStatus() + "')";

			result = template.update(sql);

		}
		return result;

	}

	// to display prescription

	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getPrescription()
	 */
	@Override
	public List<Prescription> getPrescription() {
		return template.query("select * from cm_prescriptiontable",
				new RowMapper<Prescription>() {
					public Prescription mapRow(ResultSet rs, int row)
							throws SQLException {
						Prescription prescription = new Prescription();
						prescription.setPrescId(rs.getInt(1));
						prescription.setdId(rs.getLong(2));
						prescription.setRegId(rs.getInt(3));
						return prescription;

					}
				});
	}

	// to display prescriptionDetail

	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getPrescriptionDetail()
	 */
	@Override
	public List<PrescDetail> getPrescriptionDetail() {
		return template.query("select * from cm_prescDetailtable",
				new RowMapper<PrescDetail>() {
					public PrescDetail mapRow(ResultSet rs, int row)
							throws SQLException {
						PrescDetail prescDetail = new PrescDetail();
						prescDetail.setDetailId(rs.getInt(1));
						prescDetail.setPrescId(rs.getInt(2));
						prescDetail.setMedId(rs.getInt(3));
						prescDetail.setMedFreq(rs.getString(4));
						prescDetail.setMedDays(rs.getInt(5));
						prescDetail.setPrescDate(rs.getString(6));
						prescDetail.setPharmacyStatus(rs.getString(7));
						return prescDetail;

					}
				});
	}

	// //update patient

	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#updatePatient(com.ust.model.Patient)
	 */
	@Override
	public int updatePatient(Patient pa) {

		SimpleDateFormat ft = new SimpleDateFormat("YYYY-MM-dd");
		String sqlDate = ft.format(pa.getDOB());

		String sql = "update cm_patientTable set pFName='" + pa.getpFName()
				+ "',pLName ='" + pa.getpLName() + "',pGender ='"
				+ pa.getpGender() + "', DOB = " + "TO_DATE('" + sqlDate
				+ "','YYYY-MM-dd')" + ",pAddr ='" + pa.getpAddr()
				+ "', pPhNo = '" + pa.getpPhNo() + "', pBloodGrp = '"
				+ pa.getpBloodGrp() + "',createdDate=" + "TO_DATE('"
				+ java.time.LocalDate.now() + "','YYYY-MM-DD')"
				+ "where regId = " + pa.getRegId();
		return template.update(sql);

	}

	// //update doctor

	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#updateDoctor(int, com.ust.model.DoctorStaff, java.lang.String)
	 */
	@Override
	public int updateDoctor(int sId, DoctorStaff doc, String roleName) {

		java.util.Date date = new java.util.Date();
		long t = date.getTime();
		java.sql.Date sqlDate = new java.sql.Date(t);

		AdminRole ad = getRoleName(roleName);
		System.out.println(doc.getdId());
		System.out.println("roleId=" + ad.getRoleId());
		String sql = "update cm_staffTable set roleId=" + ad.getRoleId()
				+ " ,sName='" + doc.getsName() + "' ,DOB=" + "TO_DATE('"
				+ sqlDate + "','YYYY-MM-dd')" + " ,sGender='"
				+ doc.getsGender() + "',sAddr='" + doc.getsAddr() + "',sExp='"
				+ doc.getsExp() + "',sPhNo='" + doc.getsPhNo() + "',"
				+ "sEmail='" + doc.getsEmail() + "',username='"
				+ doc.getUsername() + "',password='" + doc.getPassword()
				+ "',isActive='" + doc.getIsActive() + "' ,createdDate="
				+ "TO_DATE('" + java.time.LocalDate.now() + "','YYYY-MM-DD')"
				+ "where sId =" + sId;
		template.update(sql);

		// Integer maxId = getSequence();

		String sql1 = "update cm_doctorTable set sId=" + sId + ",dSpec='"
				+ doc.getdSpec() + "',dQualification='"
				+ doc.getdQualification() + "',consultFee="
				+ doc.getConsultFee() + "where dId = " + doc.getdId();

		template.update(sql1);

		// Integer maxDid =getDocSequence();
		int cId = getConsltationId(doc.getdId());
		String sql2 = "update cm_consultationDays set dId=" + doc.getdId()
				+ ",sunday='" + doc.getSunday() + "',monday='"
				+ doc.getMonday() + "',tuesday='" + doc.getTuesday()
				+ "',wednesday='" + doc.getWednesday() + "',thursday='"
				+ doc.getThursday() + "',friday='" + doc.getFriday()
				+ "',saturday='" + doc.getSaturday()
				+ "' where consultDayId = " + cId;

		return template.update(sql2);

	}

	private int getConsltationId(int dId) {
		// TODO Auto-generated method stub
		String sql = "select consultDayId from cm_consultationDays where dId="
				+ dId;
		int seq = template.queryForObject(sql, new Object[] {}, Integer.class);
		return seq;
	}

	// update staff
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#updateStaff(com.ust.model.Staff, java.lang.String)
	 */
	@Override
	public int updateStaff(Staff st, String roleName) {

		SimpleDateFormat ft = new SimpleDateFormat("YYYY-MM-dd");
		String sqlDate = ft.format(st.getDOB());

		AdminRole ad = getRoleName(roleName);
		System.out.println("roleId=" + ad.getRoleId());
		String sql = "update cm_staffTable set roleId=" + ad.getRoleId()
				+ ",sName='" + st.getsName() + "',DOB=" + "TO_DATE('" + sqlDate
				+ "','YYYY-MM-dd')" + ",sGender='" + st.getsGender()
				+ "',sAddr='" + st.getsAddr() + "',sExp='" + st.getsExp()
				+ "',sPhNo='" + st.getsPhNo() + "',sEmail='" + st.getsEmail()
				+ "',username='" + st.getUsername() + "',password='"
				+ st.getPassword() + "',isActive='" + st.getIsActive()
				+ "',createdDate=" + "TO_DATE('" + java.time.LocalDate.now()
				+ "','YYYY-MM-DD')" + "where sId =" + st.getsId();
		return template.update(sql);

	}

	// retrieve patient history

	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getPatHistory(int)
	 */
	@Override
	public List<PatHistory> getPatHistory(int regId) {

		String sql = "SELECT To_char(cm_doctorObsTable.obserDate,'yyyy-mm-dd'),cm_doctorObsTable.obsNotes,cm_staffTable.sName,CM_PRESCRIPTIONTABLE.prescId,cm_medicineTable.medName,cm_LabTestTable.lName,cm_LabTestTable.labId"
				+ " from cm_doctorObsTable join cm_doctorTable on cm_doctorObsTable.dId=cm_doctorTable.dId join CM_staffTABLE on"
				+ " cm_doctortable.sid=cm_stafftable.sid join cm_prescriptiontable on cm_doctortable.did=cm_prescriptiontable.did join"
				+ " cm_prescdetailtable on cm_prescriptiontable.prescid=cm_prescdetailtable.prescid join"
				+ " cm_medicinetable on cm_prescdetailtable.medid=cm_medicinetable.medid join cm_assignlabtable on cm_doctortable.did=cm_assignlabtable.did and cm_assignlabtable.regId="
				+ regId
				+ " join"
				+ " cm_labtesttable on cm_assignlabtable.labid=cm_labtesttable.labid where CM_PRESCRIPTIONTABLE.regid= "
				+ regId + "order by cm_doctorObsTable.obserDate ";

		return template.query(sql, new RowMapper<PatHistory>() {
			public PatHistory mapRow(ResultSet rs, int row) throws SQLException {
				PatHistory patHistory = new PatHistory();
				patHistory.setObserDate(rs.getString(1));
				patHistory.setObsNotes(rs.getString(2));
				patHistory.setsName(rs.getString(3));
				patHistory.setPrescId(rs.getInt(4));
				patHistory.setMedName(rs.getString(5));
				patHistory.setLname(rs.getString(6));
				patHistory.setLabId(rs.getInt(7));
				return patHistory;

			}
		});

	}

	// get Lab History
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getlabHistory(int)
	 */
	@Override
	public List<PatHistory> getlabHistory(int regId) {

		return template
				.query("SELECT cm_LabTestTable.lName,cm_LabTestTable.labId,To_char(cm_assignlabTable.assiglabDate,'yyyy-mm-dd') "
						+ " from cm_labTestTable join cm_assignlabTable on cm_labTestTable.labId=cm_assignlabTable.labId where cm_assignlabTable.regId= "
						+ regId + " order by cm_assignlabTable.assiglabDate",
						new RowMapper<PatHistory>() {
							public PatHistory mapRow(ResultSet rs, int row)
									throws SQLException {
								PatHistory patHistory = new PatHistory();
								patHistory.setLname(rs.getString(1));
								patHistory.setLabId(rs.getInt(2));
								patHistory.setAssignLabdate(rs.getString(3));
								return patHistory;

							}
						});

	}

	// get Medicine History
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getMedicineHistory(int)
	 */
	@Override
	public List<PatHistory> getMedicineHistory(int regId) {

		return template
				.query("SELECT cm_prescriptionTable.prescId,cm_medicineTable.medName,To_char(cm_prescdetailTable.prescdate,'yyyy-mm-dd')"
						+ " from cm_medicineTable join cm_prescdetailTable on cm_medicineTable.medId=cm_prescdetailTable.medId join cm_prescriptionTable on cm_prescdetailTable.prescId=cm_prescriptionTable.prescId where cm_prescriptionTable.regId= "
						+ regId + " order by cm_prescdetailTable.prescdate ",
						new RowMapper<PatHistory>() {
							public PatHistory mapRow(ResultSet rs, int row)
									throws SQLException {
								PatHistory patHistory = new PatHistory();
								patHistory.setPrescId(rs.getInt(1));
								patHistory.setMedName(rs.getString(2));
								patHistory.setPrecsDAte(rs.getString(3));
								return patHistory;

							}
						});

	}

	// get Obervation History
	/* (non-Javadoc)
	 * @see com.ust.dao.AdminDaoService#getObservationHistory(int)
	 */
	@Override
	public List<PatHistory> getObservationHistory(int regId) {

		return template
				.query("SELECT cm_staffTable.sName,cm_doctorObsTable.obsnotes,To_char(cm_doctorObsTable.obserdate,'yyyy-mm-dd')"
						+ " from cm_staffTable join cm_doctorTable on cm_staffTable.sId=cm_doctorTable.sId join cm_doctorObsTable on cm_doctorTable.dId=cm_doctorObsTable.dId where cm_doctorObsTable.regId= "
						+ regId + " order by cm_doctorObsTable.obserdate ",
						new RowMapper<PatHistory>() {
							public PatHistory mapRow(ResultSet rs, int row)
									throws SQLException {
								PatHistory patHistory = new PatHistory();
								patHistory.setsName(rs.getString(1));
								patHistory.setObsNotes(rs.getString(2));
								patHistory.setObserDate(rs.getString(3));
								return patHistory;

							}
						});

	}

}
