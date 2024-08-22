package com.nick.pulsar.api.tenant.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.admin.PulsarAdminException;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.common.policies.data.TenantInfo;

import java.util.List;
import java.util.Set;

import static com.nick.pulsar.api.constant.PulsarConstant.SERVICE_HTTP_URL;

@Slf4j
public class PulsarAdminUtil {

    private PulsarAdmin pulsarAdmin;

    public PulsarAdminUtil() {
        try {
            pulsarAdmin = PulsarAdmin.builder()
                    .serviceHttpUrl(SERVICE_HTTP_URL)
                    .build();
        } catch (PulsarClientException e) {
            log.error("PulsarClientException: {}", e);
        }

    }

    public List<String> queryAvailableCluster() {
        try {
            List<String> clusters = pulsarAdmin.clusters().getClusters();
            log.info("Query All Available Clusters: ");
            clusters.stream().forEach(cluster -> {
                log.info("Name: {}", cluster);
            });
            return clusters;
        } catch (PulsarAdminException e) {
            log.error("PulsarAdminException [getClusters]: {}", e);
        }
        return null;
    }

    public List<String> queryAvailableTenants() {
        try {
            List<String> tenants = pulsarAdmin.tenants().getTenants();
            log.info("Query Available Tenants: ");
            tenants.stream().forEach(tenant -> {
                log.info("Name: {}", tenant);
            });
            return tenants;
        } catch (PulsarAdminException e) {
            log.error("PulsarAdminException [getTenants]: {}", e);
        }
        return null;
    }

    public void createTenant(String tenantName, Set<String> clusters) {
        try {
            TenantInfo tenantInfo = TenantInfo.builder()
                    .allowedClusters(clusters)
                    .build();
            pulsarAdmin.tenants().createTenant(tenantName, tenantInfo);
            log.info("Created tenant {} on cluster", tenantName);
        } catch (PulsarAdminException e) {
            log.error("PulsarAdminException [createTenant]: ", e);
        }
    }

    public void deleteTenant(String tenantName) {
        try {
            pulsarAdmin.tenants().deleteTenant(tenantName);
            log.info("Deleted tenant {} on cluster", tenantName);
        } catch (PulsarAdminException e) {
            log.error("PulsarAdminException [deleteTenant]: ", e);
        }
    }

    public void closePulsarAdmin() {
        pulsarAdmin.close();
        log.info("Close Pulsar Admin Object");
    }
}
