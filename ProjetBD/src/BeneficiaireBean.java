import java.util.Date;


public class BeneficiaireBean {
	private int num;
	private String sexe;
	private String regimeSocial;
	private Date dateNaissance;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getRegimeSocial() {
		return regimeSocial;
	}
	public void setRegimeSocial(String regimeSocial) {
		this.regimeSocial = regimeSocial;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public BeneficiaireBean(int num, String sexe, String regimeSocial,
			Date dateNaissance) {
		super();
		this.num = num;
		this.sexe = sexe;
		this.regimeSocial = regimeSocial;
		this.dateNaissance = dateNaissance;
	}
	
	
}
