package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String name;
    private String category;
    private Double price;
    private String description;
    private String subCategory;
    private List<String> imageUrl;
    private boolean featured;
}
