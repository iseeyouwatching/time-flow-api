package ru.hits.timeflowapi.service.helpingservices;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.BadRequestException;

/**
 * Вспомогательный сервис для проверки на корректность данных необходимых для пагинации
 */
@Service
@RequiredArgsConstructor
public class CheckPaginationInfoService {
    /**
     * Метод для проверки на корректность номера страницы
     *
     * @param pageNumber номер страницы
     * @throws BadRequestException некорректный запрос
     */
    public void checkPageNumber(int pageNumber) {
        if (pageNumber < 0) {
            throw new BadRequestException("Номер страницы не может быть отрицательной");
        }
    }

    /**
     * Метод для проверки на корректность размера страницы
     *
     * @param pageSize размер страницы
     * @throws BadRequestException некорректный запрос
     */
    public void checkPageSize(int pageSize) {
        if (pageSize <= 0) {
            throw new BadRequestException("Размер страницы должен быть больше нуля");
        }
    }

    /**
     * Метод для проверки на null направления сортировки
     *
     * @param direction направление сортировки
     * @throws BadRequestException некорректный запрос
     */
    public void checkDirection(Sort.Direction direction) {
        if (direction == null) {
            throw new BadRequestException("Направление сортировки не должно быть null.");
        }
    }

    /**
     * Обобщающий метод для проверки всех данных необходимых для пагинации
     *
     * @param direction  направление сортировки
     * @param pageNumber номер страницы
     * @param pageSize   размер страницы
     */
    public void checkPagination(int pageNumber, int pageSize, Sort.Direction direction) {
        checkPageNumber(pageNumber);
        checkPageSize(pageSize);
        checkDirection(direction);
    }


}
