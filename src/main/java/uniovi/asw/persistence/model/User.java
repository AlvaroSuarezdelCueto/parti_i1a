package uniovi.asw.persistence.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TUsers")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String surname;
	private String password;
	@Column(unique = true, nullable = false)
	private String email;
	private String nationality;
	@Column(unique = true)
	private String DNI;
	private String address;
	private Date birthDate;

	public User() {
	}

	@OneToMany(mappedBy = "user")
	private Set<Proposal> proposals = new HashSet<Proposal>();
	
	@OneToMany(mappedBy = "user")
	private Set<Vote> votes = new HashSet<>();

	@OneToMany(mappedBy = "user")
	private Set<Comment> comments = new HashSet<Comment>();
	
	public User(String name, String surname, String password, String email, String nationality, String DNI,
			String address, Date birthDate) {
		super();
		setName(name);
		setSurname(surname);
		setPassword(password);
		setEmail(email);
		setNationality(nationality);
		setDNI(DNI);
		setAddress(address);
		setBirthDate(birthDate);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (DNI == null) {
			if (other.DNI != null)
				return false;
		} else if (!DNI.equals(other.DNI))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public Set<Proposal> getProposals() {
		return new HashSet<Proposal>(proposals);
	}

	Set<Proposal> _getProposals() {
		return proposals;
	}

	public Set<Comment> getComments() {
		return new HashSet<Comment>(comments);
	}

	Set<Comment> _getComments() {
		return comments;
	}

	Set<Vote> _getVotes() {
		return votes;
	}
	
	public Set<Vote> getVotes() {
		return new HashSet<Vote>(votes);
	}
	
	public void makeProposal(Proposal proposal){
		Association.MakeProposal.link(this,proposal);
	}
	
	public void vote(Vote vote, Votable votable){
		Association.Votation.link(this, vote, votable);
	}
	
	public void comment(Proposal proposal, Comment comment){
		Association.MakeComment.link(this,comment,proposal);
	}
	
	public void deleteProposal(Proposal proposal){
		Association.MakeProposal.unlink(this,proposal);
	}	
	
	public void deleteComment(Proposal proposal, Comment comment){
		Association.MakeComment.unlink(this,comment,proposal);
	}
	
	public void deleteVote(Vote vote, Proposal proposal){
		Association.Votation.unlink(this, vote, proposal);
	}
}
