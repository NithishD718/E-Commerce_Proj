package InventoryModel.ProductManagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductFilter {

    private Map<String, Predicate<Product>> filterMap;

    public ProductFilter()
    {
      filterMap =  new HashMap<>();
    }

    private static Predicate<Product> getFiltersPredicate(String filterType , String filterValue)
    {
        switch (filterType.toLowerCase())
        {
            case "price" ->{
                if(filterValue.contains("-")) {
                    String[] priceRange = filterValue.split("-");
                    double minPrice = Double.parseDouble(priceRange[0]);
                    double maxPrice = Double.parseDouble(priceRange[1]);
                    return product ->product.getPrice() >= minPrice && product.getPrice() <= maxPrice;
                }
            }
            case "brand" ->{
                return product -> product.getBrand().equalsIgnoreCase(filterValue);
            }
            default -> {
            return product -> true;
           }
        }
        return null;
    }

    public static Map<Integer,Product> applyFilter(HashMap<Integer,Product> productMap , List<FilterCriteria> filters)
    {
        try {
            List<Predicate<Product>> predicates = filters.stream().map(filterCriteria -> {
                String filterType = filterCriteria.getFilterType();
                String filterValue = filterCriteria.getFilterValue();
                return getFiltersPredicate(filterType, filterValue);
            }).collect(Collectors.toList());

            Predicate<Product> combinedPredicate = product -> predicates.stream().allMatch(predicate -> predicate.test(product));
            return productMap.entrySet().stream()
                    .filter(entry -> combinedPredicate.test(entry.getValue()))
                    .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
