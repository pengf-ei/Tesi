package my.tesi.questionario.entity;

public class ReplyDomanda extends FormDomanda {

	private int idrispostadata;
	
	public ReplyDomanda() {
		super();
	}

	public int getIdrispostadata() {
		return idrispostadata;
	}

	public void setIdrispostadata(int idrispostadata) {
		this.idrispostadata = idrispostadata;
	}

	@Override
	public String toString() {
		return "ReplyDomanda [idrispostadata=" + idrispostadata + ", toString()=" + super.toString() + "]";
	}

	

}
