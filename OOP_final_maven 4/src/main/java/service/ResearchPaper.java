package service;

import java.util.Date;
import java.util.List;

public class ResearchPaper {
    private String title;
    private List<String> authors; // Список авторов
    private String journal;
    private int citations; // Количество цитирований
    private String doi; // Уникальный идентификатор статьи
    private int pages;
    private Date publicationDate;

    public ResearchPaper(String title, List<String> authors, String journal, int citations,
                         String doi, int pages, Date publicationDate) {
        this.title = title;
        this.authors = authors;
        this.journal = journal;
        this.citations = citations;
        this.doi = doi;
        this.pages = pages;
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getJournal() {
        return journal;
    }

    public int getCitations() {
        return citations;
    }

    public String getDoi() {
        return doi;
    }

    public int getPages() {
        return pages;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public String getCitation(String format) {
        if ("Bibtex".equalsIgnoreCase(format)) {
            return "@article{" + doi + ",\n"
                    + "  title={" + title + "},\n"
                    + "  author={" + String.join(", ", authors) + "},\n"
                    + "  journal={" + journal + "},\n"
                    + "  year=" + (publicationDate.getYear() + 1900) + ",\n"
                    + "  pages=" + pages + "\n"
                    + "}";
        } else { // Default to plain text
            return title + " by " + String.join(", ", authors)
                    + " in " + journal + ", DOI: " + doi;
        }
    }
}
