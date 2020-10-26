package com.perfectcherry.dto;

import java.io.Serializable;
import java.util.List;

public class MailDTO implements Serializable{

	private static final long serialVersionUID = 9176624068338268608L;

	private String mailFrom;

	private String mailTo;

	private String mailCc;

	private String mailBcc;

	private String mailSubject;

	private String mailContent;

	private String contentType;

	private List<Object> attachments;

	public MailDTO() {
		contentType = "text/plain";
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailCc() {
		return mailCc;
	}

	public void setMailCc(String mailCc) {
		this.mailCc = mailCc;
	}

	public String getMailBcc() {
		return mailBcc;
	}

	public void setMailBcc(String mailBcc) {
		this.mailBcc = mailBcc;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public List<Object> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Object> attachments) {
		this.attachments = attachments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attachments == null) ? 0 : attachments.hashCode());
		result = prime * result + ((contentType == null) ? 0 : contentType.hashCode());
		result = prime * result + ((mailBcc == null) ? 0 : mailBcc.hashCode());
		result = prime * result + ((mailCc == null) ? 0 : mailCc.hashCode());
		result = prime * result + ((mailContent == null) ? 0 : mailContent.hashCode());
		result = prime * result + ((mailFrom == null) ? 0 : mailFrom.hashCode());
		result = prime * result + ((mailSubject == null) ? 0 : mailSubject.hashCode());
		result = prime * result + ((mailTo == null) ? 0 : mailTo.hashCode());
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
		MailDTO other = (MailDTO) obj;
		if (attachments == null) {
			if (other.attachments != null)
				return false;
		} else if (!attachments.equals(other.attachments))
			return false;
		if (contentType == null) {
			if (other.contentType != null)
				return false;
		} else if (!contentType.equals(other.contentType))
			return false;
		if (mailBcc == null) {
			if (other.mailBcc != null)
				return false;
		} else if (!mailBcc.equals(other.mailBcc))
			return false;
		if (mailCc == null) {
			if (other.mailCc != null)
				return false;
		} else if (!mailCc.equals(other.mailCc))
			return false;
		if (mailContent == null) {
			if (other.mailContent != null)
				return false;
		} else if (!mailContent.equals(other.mailContent))
			return false;
		if (mailFrom == null) {
			if (other.mailFrom != null)
				return false;
		} else if (!mailFrom.equals(other.mailFrom))
			return false;
		if (mailSubject == null) {
			if (other.mailSubject != null)
				return false;
		} else if (!mailSubject.equals(other.mailSubject))
			return false;
		if (mailTo == null) {
			if (other.mailTo != null)
				return false;
		} else if (!mailTo.equals(other.mailTo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MailDTO [mailFrom=" + mailFrom + ", mailTo=" + mailTo + ", mailCc=" + mailCc + ", mailBcc=" + mailBcc
				+ ", mailSubject=" + mailSubject + ", mailContent=" + mailContent + ", contentType=" + contentType
				+ ", attachments=" + attachments + "]";
	}

}
