package za.ac.cput.domain.enums;

public enum RoomType {
    SINGLE(1),
    DOUBLE(2),
    SUITE(4),
    PENTHOUSE(6);

    RoomType(int capacity) {
    }
}