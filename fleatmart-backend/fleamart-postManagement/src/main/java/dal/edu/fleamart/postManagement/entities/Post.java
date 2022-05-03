package dal.edu.fleamart.postManagement.entities;

import java.util.Calendar;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

import dal.edu.fleamart.postManagement.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@DynamoDBTable(tableName = "Post")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Post implements PostAttribute {

	private String id;

	private String userId;

	private Calendar createdDate;

	private String productName;

	private ProductCategory productCategory;

	private String description;

	private Map<String, String> imageUrls;

	private Float price;

	private String currency;

	private Condition condition;

	private Address sellerAddress;
	
	private String sellerContact1;
	
	private String sellerContact2;

	private Status status;

	private Calendar soldDate;

	@DynamoDBHashKey(attributeName = ID)
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return id;
	}

	@DynamoDBAttribute(attributeName = USER_ID)
	public String getUserId() {
		return userId;
	}

	@DynamoDBAttribute(attributeName = CREATED_DATE)
	public Calendar getCreatedDate() {
		return createdDate;
	}

	@DynamoDBAttribute(attributeName = PRODUCT_NAME)
	public String getProductName() {
		return productName;
	}

	@DynamoDBAttribute(attributeName = PRODUCT_CATEGORY)
	@DynamoDBTypeConvertedEnum
	public ProductCategory getProductCategory() {
		return productCategory;
	}

	@DynamoDBAttribute(attributeName = DESCRIPTION)
	public String getDescription() {
		return description;
	}

	@DynamoDBAttribute(attributeName = IMAGE_URLS)
	public Map<String, String> getImageUrls() {
		return imageUrls;
	}

	@DynamoDBAttribute(attributeName = PRICE)
	public Float getPrice() {
		return price;
	}

	@DynamoDBAttribute(attributeName = CURRENCY)
	public String getCurrency() {
		return currency;
	}

	@DynamoDBAttribute(attributeName = CONDITION)
	@DynamoDBTypeConvertedEnum
	public Condition getCondition() {
		return condition;
	}

	@DynamoDBAttribute(attributeName = SELLER_ADDRESS)
	public Address getSellerAddress() {
		return sellerAddress;
	}

	@DynamoDBAttribute(attributeName = SELLER_CONTACT_1)
	public String getSellerContact1() {
		return sellerContact1;
	}

	@DynamoDBAttribute(attributeName = SELLER_CONTACT_2)
	public String getSellerContact2() {
		return sellerContact2;
	}
	
	@DynamoDBAttribute(attributeName = ITEM_STATUS)
	@DynamoDBTypeConvertedEnum
	public Status getStatus() {
		return status;
	}

	@DynamoDBAttribute(attributeName = SOLD_DATE)
	public Calendar getSoldDate() {
		return soldDate;
	}

	public static Post convertToPost(PostDto dto) {

		return Post.builder()
				.userId(dto.getUserId())
				.createdDate(null)
				.productName(dto.getProductName())
				.productCategory(ProductCategory.getCategory(dto.getProductCategory()))
				.description(dto.getDescription())
				.price(dto.getPrice())
				.currency(dto.getCurrency())
				.condition(Condition.getCondition(dto.getCondition()))
				.sellerAddress(Address.convertToAddress(dto))
				.sellerContact1(dto.getSellerContact1())
				.sellerContact2(dto.getSellerContact2())
				.status(Status.getStatus(dto.getStatus()))
				.soldDate(null)
				.build();
	}

}
