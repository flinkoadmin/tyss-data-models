package com.tyss.optimize.data.models.dto;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class ClientDetails {

    public Map<String, Long> osData = new HashMap<>();
    public Map<String, Long> browserData = new HashMap<>();
    public Map<String, Long> deviceData = new HashMap<>();
    public Map<String, Long> statusData = new HashMap<>();
    public Map<String, Long> subVersionData = new HashMap<>();
    public Map<String, Map<String, Long>> browserMap = new HashMap<>();
    public Map<String, Multimap<String, String>> osBrowserVersionData = new HashMap<>();
    public Map<String, Map<String, Long>> deviceMap = new HashMap<>();
    public Map<String, Map<String, Long>> statusMap = new HashMap<>();
    public Map<String, Multimap<String, String>> osDeviceVersionData = new HashMap<>();
    public Map<String, Long> osVersionData = new HashMap<>();
    public Multimap<String, String> browserVersionData = ArrayListMultimap.create();
    public Multimap<String, String> deviceVersionData = ArrayListMultimap.create();
    public Map<String, Map<String, Object>> accessMap = new HashMap<>();
    public Multimap<String, String> accessMapDetails = ArrayListMultimap.create();
}
