package com.tyss.optimize.data.models.dto.storage;

import com.tyss.optimize.common.util.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StoragePathService {

    public String resolvePath(String filePath, Map<String, String> filePathMap) {
        if (Objects.nonNull(filePath) && StringUtils.isNotEmpty(filePath)) {
            String[] arrayOfValue = filePath.split(CommonConstants.forwardSlash);
            List<String> listOfPath = List.of(arrayOfValue);
            Predicate<String> pattern = Pattern.compile("\\$+").asPredicate();
            listOfPath = listOfPath.stream().filter(pattern).collect(Collectors.<String>toList());
            for (String value : listOfPath) {
                value = value.substring(1);
                String actualValue = filePathMap.get(value);
                filePath = filePath.replace(CommonConstants.dollar + value, actualValue);
            }
        } else {
            log.info("File path can't be empty");
        }
        return filePath;
    }
}
