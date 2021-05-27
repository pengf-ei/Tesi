package my.tesi.questionario.entity;

import java.util.List;

public class ReplyDomanda extends FormDomanda {

	private List<Integer> idrispostadata;
	
	private String rispapertadata;
	
	public ReplyDomanda() {
		super();
	}

	public List<Integer> getIdrispostadata() {
		return idrispostadata;
	}

	public void setIdrispostadata(List<Integer> idrispostadata) {
		this.idrispostadata = idrispostadata;
	}

	public String getRispapertadata() {
		return rispapertadata;
	}

	public void setRispapertadata(String rispapertadata) {
		this.rispapertadata = rispapertadata;
	}

	@Override
	public String toString() {
		return "ReplyDomanda [idrispostadata=" + idrispostadata + ", rispapertadata=" + rispapertadata + ", toString()="
				+ super.toString() + "]";
	}


}
