package my.tesi.questionario.entity;

public class ReplyDomanda extends FormDomanda {

	private int idrispostadata;
	
	private String rispapertadata;
	
	public ReplyDomanda() {
		super();
	}

	public int getIdrispostadata() {
		return idrispostadata;
	}

	public void setIdrispostadata(int idrispostadata) {
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
