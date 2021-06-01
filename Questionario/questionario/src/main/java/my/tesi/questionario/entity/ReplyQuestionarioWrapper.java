package my.tesi.questionario.entity;

import java.util.List;

public class ReplyQuestionarioWrapper {
	
	private int id_questionario;
	
	private int punteggioTot;
	
	private List<ReplyDomanda> replyDomande;

	public int getId_questionario() {
		return id_questionario;
	}

	public void setId_questionario(int id_questionario) {
		this.id_questionario = id_questionario;
	}

	public List<ReplyDomanda> getReplyDomande() {
		return replyDomande;
	}

	public void setReplyDomande(List<ReplyDomanda> replyDomande) {
		this.replyDomande = replyDomande;
	}

	public int getPunteggioTot() {
		return punteggioTot;
	}

	public void setPunteggioTot(int punteggioTot) {
		this.punteggioTot = punteggioTot;
	}

	@Override
	public String toString() {
		return "ReplyQuestionarioWrapper [id_questionario=" + id_questionario + ", punteggioTot=" + punteggioTot
				+ ", replyDomande=" + replyDomande + "]";
	}



}
