package com.nick.pulsar.api.namespace.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.admin.PulsarAdminException;
import org.apache.pulsar.client.api.PulsarClientException;

import java.util.List;

import static com.nick.pulsar.api.constant.PulsarConstant.SERVICE_HTTP_URL;

@Slf4j
public class PulsarNamespaceUtil {
    private PulsarAdmin pulsarAdmin;

    public PulsarNamespaceUtil() {
        try {
            pulsarAdmin = PulsarAdmin.builder()
                    .serviceHttpUrl(SERVICE_HTTP_URL)
                    .build();
        } catch (PulsarClientException e) {
            log.error("PulsarClientException: {}", e);
        }
    }

    public List<String> queryAvailableNamespace(String tenant) {
        try {
            List<String> namespaces = pulsarAdmin.namespaces().getNamespaces(tenant);
            log.info("Query Available Namespace under tenant {}", tenant);
            namespaces.stream().forEach(ns -> {
                log.info("Name: {}", ns);
            });
            return namespaces;
        } catch (PulsarAdminException e) {
            log.error("PulsarAdminException [getNamespaces] ", e);
        }
        return null;
    }

    public void createNamespace(String namespaceName) {
        try {
            pulsarAdmin.namespaces().createNamespace(namespaceName);
            log.info("Created Namespace: {}", namespaceName);
        } catch (PulsarAdminException e) {
            log.error("PulsarAdminException [createNamespace] ", e);
        }
    }

    public void deleteNamespace(String namespaceName) {
        try {
            pulsarAdmin.namespaces().deleteNamespace(namespaceName);
            log.info("Deleted Namespace: {}", namespaceName);
        } catch (PulsarAdminException e) {
            log.error("PulsarAdminException [deleteNamespace] ", e);
        }
    }

    public void close() {
        pulsarAdmin.close();
        log.info("Close Pulsar Admin Object");
    }
}
