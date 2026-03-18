package org.example.parking_management_system.dto.response;

import java.util.ArrayList;
import java.util.List;

public class ParkingStatusItemDTO {
    private Integer totalSpaces;
    private Integer availableSpaces;
    private Integer occupiedSpaces;
    private Integer reservedSpaces;
    private List<AreaDetailsDTO> spaceByArea;

    public Integer getTotalSpaces() {
        return totalSpaces;
    }

    public void setTotalSpaces(Integer totalSpaces) {
        this.totalSpaces = totalSpaces;
    }

    public Integer getAvailableSpaces() {
        return availableSpaces;
    }

    public void setAvailableSpaces(Integer availableSpaces) {
        this.availableSpaces = availableSpaces;
    }

    public Integer getOccupiedSpaces() {
        return occupiedSpaces;
    }

    public void setOccupiedSpaces(Integer occupiedSpaces) {
        this.occupiedSpaces = occupiedSpaces;
    }

    public Integer getReservedSpaces() {
        return reservedSpaces;
    }

    public void setReservedSpaces(Integer reservedSpaces) {
        this.reservedSpaces = reservedSpaces;
    }

    public List<AreaDetailsDTO> getSpaceByArea() {
        if (spaceByArea == null){
            spaceByArea = new ArrayList<>();
        }
        return spaceByArea;
    }

    public void setSpaceByArea(List<AreaDetailsDTO> spaceByArea) {
        this.spaceByArea = spaceByArea;
    }
}
