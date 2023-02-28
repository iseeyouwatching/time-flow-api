package ru.hits.timeflowapi.service.helpingservices;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.BadRequestException;

@Service
@RequiredArgsConstructor
public class CheckPaginationInfoService {
    public void checkPageNumber(int pageNumber) {
        if (pageNumber < 0) {
            throw new BadRequestException("Номер страницы не может быть отрицательной");
        }
    }

    public void checkPageSize(int pageSize) {
        if (pageSize <= 0) {
            throw new BadRequestException("Размер страницы должен быть больше нуля");
        }
    }

    public void checkDirection(Sort.Direction direction) {
        if (direction == null) {
            throw new BadRequestException("Направление сортировки не должно быть null.");
        }
    }

    public void checkPagination(int pageNumber, int pageSize, Sort.Direction direction) {
        checkPageNumber(pageNumber);
        checkPageSize(pageSize);
        checkDirection(direction);
    }


}
