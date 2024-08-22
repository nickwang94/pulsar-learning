package com.nick.pulsar.api;

import com.nick.pulsar.api.namespace.util.PulsarNamespaceUtil;
import com.nick.pulsar.api.tenant.util.PulsarAdminUtil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.nick.pulsar.api.constant.PulsarConstant.*;

/**
 * Use Java API to create Pulsar Tenants
 */
public class PulsarOperation {


    private PulsarAdminUtil pulsarAdminUtil;
    private PulsarNamespaceUtil pulsarNamespaceUtil;

    public PulsarOperation() {
        pulsarAdminUtil = new PulsarAdminUtil();
        pulsarNamespaceUtil = new PulsarNamespaceUtil();
    }

    public static void main(String[] args) {
        PulsarOperation pulsarOperation = new PulsarOperation();

        /**
         * Tenant Operation
         */
//        // Query available cluster
//        List<String> cluster = pulsarOperation.pulsarAdminUtil.queryAvailableCluster();
//        // Add tenant
//        Set<String> allowedClusters = cluster.stream().collect(Collectors.toSet());
//        pulsarOperation.pulsarAdminUtil.createTenant(TEMP_TENANT_NAME, allowedClusters);
//
//        // Query All Tenants
//        pulsarOperation.pulsarAdminUtil.queryAvailableTenants();
//
//        // Delete Tenants
//        pulsarOperation.pulsarAdminUtil.deleteTenant(TEMP_TENANT_NAME);
//        pulsarOperation.pulsarAdminUtil.queryAvailableTenants();
//
//        // Close Pulsar Admin Object
//        pulsarOperation.pulsarAdminUtil.closePulsarAdmin();

        /**
         * Namespace Operation
         */
        pulsarOperation.pulsarNamespaceUtil.createNamespace(NAMESPACE);
        pulsarOperation.pulsarNamespaceUtil.queryAvailableNamespace(TENANT_NAME);
        pulsarOperation.pulsarNamespaceUtil.deleteNamespace(NAMESPACE);
        pulsarOperation.pulsarNamespaceUtil.queryAvailableNamespace(TENANT_NAME);
        pulsarOperation.pulsarNamespaceUtil.close();
    }
}
