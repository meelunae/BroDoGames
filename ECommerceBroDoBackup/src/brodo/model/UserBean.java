package brodo.model;

public class UserBean {

	public UserBean() {
		
		
	}
	
	public UserBean(int id, String nome, String cognome, String dataNascita, String username, String password, String email,
			String citta, String via, int numCivico, int cap, boolean admin) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.username = username;
		this.password = password;
		this.email = email;
		this.citta = citta;
		this.via = via;
		this.numCivico = numCivico;
		this.cap = cap;
		this.admin = admin;
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public int getNumCivico() {
		return numCivico;
	}
	public void setNumCivico(int numCivico) {
		this.numCivico = numCivico;
	}
	public int getCap() {
		return cap;
	}
	public void setCap(int cap) {
		this.cap = cap;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	private int id;
	private String nome;
	private String cognome;
	private String dataNascita;
	private String username;
	private String password;
	private String email;
	private String citta;
	private String via;
	private int numCivico;
	private int cap;
	private boolean admin;
}
