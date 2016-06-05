package by.academy.it.rentacar.viewobject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Nata on 02.06.2016.
 */
public class FilterVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String transmission;
    private int fuelId;
    private int typeId;
    private int ratingId;
    private int recordPerPage;
    private Date fromDate;
    private Date byDate;

    public FilterVO() {}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getFuelId() {
        return fuelId;
    }

    public void setFuelId(int fuelId) {
        this.fuelId = fuelId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getRecordPerPage() {
        return recordPerPage;
    }

    public void setRecordPerPage(int recordPerPage) {
        this.recordPerPage = recordPerPage;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getByDate() {
        return byDate;
    }

    public void setByDate(Date byDate) {
        this.byDate = byDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilterVO filtersVO = (FilterVO) o;

        if (fuelId != filtersVO.fuelId) return false;
        if (typeId != filtersVO.typeId) return false;
        if (ratingId != filtersVO.ratingId) return false;
        if (recordPerPage != filtersVO.recordPerPage) return false;
        if (transmission != null ? !transmission.equals(filtersVO.transmission) : filtersVO.transmission != null)
            return false;
        if (fromDate != null ? !fromDate.equals(filtersVO.fromDate) : filtersVO.fromDate != null) return false;
        return byDate != null ? byDate.equals(filtersVO.byDate) : filtersVO.byDate == null;

    }

    @Override
    public int hashCode() {
        int result = transmission != null ? transmission.hashCode() : 0;
        result = 31 * result + fuelId;
        result = 31 * result + typeId;
        result = 31 * result + ratingId;
        result = 31 * result + recordPerPage;
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (byDate != null ? byDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "filtersVO{" +
                "transmission='" + transmission + '\'' +
                ", fuelId=" + fuelId +
                ", typeId=" + typeId +
                ", ratingId=" + ratingId +
                ", recordPerPage=" + recordPerPage +
                ", fromDate='" + fromDate + '\'' +
                ", byDate='" + byDate + '\'' +
                '}';
    }
}
