

public class DouBan_Book  {

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getIsbn10() {
		return isbn10;
	}


	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}


	public String getIsbn13() {
		return isbn13;
	}


	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public String getPubdate() {
		return pubdate;
	}


	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}


	public String getBinding() {
		return binding;
	}


	public void setBinding(String binding) {
		this.binding = binding;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getPages() {
		return pages;
	}


	public void setPages(String pages) {
		this.pages = pages;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}

	private String id;// 书本ID号
	private String isbn10;// 10位ISBN
	private String isbn13;
	private String title;
	private String url;
	private String imagePath;
	private String author;
	private String publisher;
	private String pubdate;
	private String binding;
	private String price;
	private String pages;
	private String summary;
	

public DouBan_Book() {
	id="暂无ID";
    isbn10="暂无isbn10";// 10位ISBN
	isbn13="";
	title="暂无title";
	url="暂无url";
	imagePath="暂无图片信息";
	author="暂无作者";
	publisher="暂无出版社";
	pubdate="暂无出版时间";
	binding="暂无绑定方式";
	price="暂无价格";
	pages="暂无页数";
	summary="暂无简介";
	
	   
   }

}