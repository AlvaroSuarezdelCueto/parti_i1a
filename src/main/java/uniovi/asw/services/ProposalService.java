package uniovi.asw.services;

import java.util.List;

import uniovi.asw.persistence.model.Proposal;

public interface ProposalService {
	
	void save(Proposal proposal);
	boolean checkExists(Long id);
	List<Proposal> findAll();
	Proposal findById(Long id);

}
