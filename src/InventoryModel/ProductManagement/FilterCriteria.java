package InventoryModel.ProductManagement;

public class FilterCriteria {
    private String filterType;
    private String filterValue;

    public FilterCriteria(String filterType , String filterValue)
    {
        this.filterType = filterType;
        this.filterValue = filterValue;
    }

    public String getFilterType()
    {
        return filterType;
    }

    public String getFilterValue()
    {
        return filterValue;
    }
}
