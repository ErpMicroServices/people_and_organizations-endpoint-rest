package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final IoLibraryAccessors laccForIoLibraryAccessors = new IoLibraryAccessors(owner);
    private final OrgLibraryAccessors laccForOrgLibraryAccessors = new OrgLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Returns the group of libraries at io
     */
    public IoLibraryAccessors getIo() {
        return laccForIoLibraryAccessors;
    }

    /**
     * Returns the group of libraries at org
     */
    public OrgLibraryAccessors getOrg() {
        return laccForOrgLibraryAccessors;
    }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class IoLibraryAccessors extends SubDependencyFactory {
        private final IoHypersistenceLibraryAccessors laccForIoHypersistenceLibraryAccessors = new IoHypersistenceLibraryAccessors(owner);

        public IoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at io.hypersistence
         */
        public IoHypersistenceLibraryAccessors getHypersistence() {
            return laccForIoHypersistenceLibraryAccessors;
        }

    }

    public static class IoHypersistenceLibraryAccessors extends SubDependencyFactory {
        private final IoHypersistenceHypersistenceLibraryAccessors laccForIoHypersistenceHypersistenceLibraryAccessors = new IoHypersistenceHypersistenceLibraryAccessors(owner);

        public IoHypersistenceLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at io.hypersistence.hypersistence
         */
        public IoHypersistenceHypersistenceLibraryAccessors getHypersistence() {
            return laccForIoHypersistenceHypersistenceLibraryAccessors;
        }

    }

    public static class IoHypersistenceHypersistenceLibraryAccessors extends SubDependencyFactory {
        private final IoHypersistenceHypersistenceUtilsLibraryAccessors laccForIoHypersistenceHypersistenceUtilsLibraryAccessors = new IoHypersistenceHypersistenceUtilsLibraryAccessors(owner);

        public IoHypersistenceHypersistenceLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at io.hypersistence.hypersistence.utils
         */
        public IoHypersistenceHypersistenceUtilsLibraryAccessors getUtils() {
            return laccForIoHypersistenceHypersistenceUtilsLibraryAccessors;
        }

    }

    public static class IoHypersistenceHypersistenceUtilsLibraryAccessors extends SubDependencyFactory {
        private final IoHypersistenceHypersistenceUtilsHibernateLibraryAccessors laccForIoHypersistenceHypersistenceUtilsHibernateLibraryAccessors = new IoHypersistenceHypersistenceUtilsHibernateLibraryAccessors(owner);

        public IoHypersistenceHypersistenceUtilsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at io.hypersistence.hypersistence.utils.hibernate
         */
        public IoHypersistenceHypersistenceUtilsHibernateLibraryAccessors getHibernate() {
            return laccForIoHypersistenceHypersistenceUtilsHibernateLibraryAccessors;
        }

    }

    public static class IoHypersistenceHypersistenceUtilsHibernateLibraryAccessors extends SubDependencyFactory {

        public IoHypersistenceHypersistenceUtilsHibernateLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for v62 (io.hypersistence:hypersistence-utils-hibernate-62)
         * with versionRef 'io.hypersistence.hypersistence.utils.hibernate.v62'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getV62() {
                return create("io.hypersistence.hypersistence.utils.hibernate.v62");
        }

    }

    public static class OrgLibraryAccessors extends SubDependencyFactory {
        private final OrgApacheLibraryAccessors laccForOrgApacheLibraryAccessors = new OrgApacheLibraryAccessors(owner);
        private final OrgHibernateLibraryAccessors laccForOrgHibernateLibraryAccessors = new OrgHibernateLibraryAccessors(owner);
        private final OrgPostgresqlLibraryAccessors laccForOrgPostgresqlLibraryAccessors = new OrgPostgresqlLibraryAccessors(owner);
        private final OrgProjectlombokLibraryAccessors laccForOrgProjectlombokLibraryAccessors = new OrgProjectlombokLibraryAccessors(owner);
        private final OrgSpringframeworkLibraryAccessors laccForOrgSpringframeworkLibraryAccessors = new OrgSpringframeworkLibraryAccessors(owner);
        private final OrgWiremockLibraryAccessors laccForOrgWiremockLibraryAccessors = new OrgWiremockLibraryAccessors(owner);

        public OrgLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.apache
         */
        public OrgApacheLibraryAccessors getApache() {
            return laccForOrgApacheLibraryAccessors;
        }

        /**
         * Returns the group of libraries at org.hibernate
         */
        public OrgHibernateLibraryAccessors getHibernate() {
            return laccForOrgHibernateLibraryAccessors;
        }

        /**
         * Returns the group of libraries at org.postgresql
         */
        public OrgPostgresqlLibraryAccessors getPostgresql() {
            return laccForOrgPostgresqlLibraryAccessors;
        }

        /**
         * Returns the group of libraries at org.projectlombok
         */
        public OrgProjectlombokLibraryAccessors getProjectlombok() {
            return laccForOrgProjectlombokLibraryAccessors;
        }

        /**
         * Returns the group of libraries at org.springframework
         */
        public OrgSpringframeworkLibraryAccessors getSpringframework() {
            return laccForOrgSpringframeworkLibraryAccessors;
        }

        /**
         * Returns the group of libraries at org.wiremock
         */
        public OrgWiremockLibraryAccessors getWiremock() {
            return laccForOrgWiremockLibraryAccessors;
        }

    }

    public static class OrgApacheLibraryAccessors extends SubDependencyFactory {
        private final OrgApacheCommonsLibraryAccessors laccForOrgApacheCommonsLibraryAccessors = new OrgApacheCommonsLibraryAccessors(owner);

        public OrgApacheLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.apache.commons
         */
        public OrgApacheCommonsLibraryAccessors getCommons() {
            return laccForOrgApacheCommonsLibraryAccessors;
        }

    }

    public static class OrgApacheCommonsLibraryAccessors extends SubDependencyFactory {
        private final OrgApacheCommonsCommonsLibraryAccessors laccForOrgApacheCommonsCommonsLibraryAccessors = new OrgApacheCommonsCommonsLibraryAccessors(owner);

        public OrgApacheCommonsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.apache.commons.commons
         */
        public OrgApacheCommonsCommonsLibraryAccessors getCommons() {
            return laccForOrgApacheCommonsCommonsLibraryAccessors;
        }

    }

    public static class OrgApacheCommonsCommonsLibraryAccessors extends SubDependencyFactory {

        public OrgApacheCommonsCommonsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for lang3 (org.apache.commons:commons-lang3)
         * with versionRef 'org.apache.commons.commons.lang3'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLang3() {
                return create("org.apache.commons.commons.lang3");
        }

    }

    public static class OrgHibernateLibraryAccessors extends SubDependencyFactory {
        private final OrgHibernateValidatorLibraryAccessors laccForOrgHibernateValidatorLibraryAccessors = new OrgHibernateValidatorLibraryAccessors(owner);

        public OrgHibernateLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.hibernate.validator
         */
        public OrgHibernateValidatorLibraryAccessors getValidator() {
            return laccForOrgHibernateValidatorLibraryAccessors;
        }

    }

    public static class OrgHibernateValidatorLibraryAccessors extends SubDependencyFactory {
        private final OrgHibernateValidatorHibernateLibraryAccessors laccForOrgHibernateValidatorHibernateLibraryAccessors = new OrgHibernateValidatorHibernateLibraryAccessors(owner);

        public OrgHibernateValidatorLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.hibernate.validator.hibernate
         */
        public OrgHibernateValidatorHibernateLibraryAccessors getHibernate() {
            return laccForOrgHibernateValidatorHibernateLibraryAccessors;
        }

    }

    public static class OrgHibernateValidatorHibernateLibraryAccessors extends SubDependencyFactory {

        public OrgHibernateValidatorHibernateLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for validator (org.hibernate.validator:hibernate-validator)
         * with versionRef 'org.hibernate.validator.hibernate.validator'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getValidator() {
                return create("org.hibernate.validator.hibernate.validator");
        }

    }

    public static class OrgPostgresqlLibraryAccessors extends SubDependencyFactory {

        public OrgPostgresqlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for postgresql (org.postgresql:postgresql)
         * with versionRef 'org.postgresql.postgresql'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPostgresql() {
                return create("org.postgresql.postgresql");
        }

    }

    public static class OrgProjectlombokLibraryAccessors extends SubDependencyFactory {

        public OrgProjectlombokLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for lombok (org.projectlombok:lombok)
         * with versionRef 'org.projectlombok.lombok'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLombok() {
                return create("org.projectlombok.lombok");
        }

    }

    public static class OrgSpringframeworkLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootLibraryAccessors laccForOrgSpringframeworkBootLibraryAccessors = new OrgSpringframeworkBootLibraryAccessors(owner);

        public OrgSpringframeworkLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springframework.boot
         */
        public OrgSpringframeworkBootLibraryAccessors getBoot() {
            return laccForOrgSpringframeworkBootLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringLibraryAccessors laccForOrgSpringframeworkBootSpringLibraryAccessors = new OrgSpringframeworkBootSpringLibraryAccessors(owner);

        public OrgSpringframeworkBootLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springframework.boot.spring
         */
        public OrgSpringframeworkBootSpringLibraryAccessors getSpring() {
            return laccForOrgSpringframeworkBootSpringLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringBootLibraryAccessors laccForOrgSpringframeworkBootSpringBootLibraryAccessors = new OrgSpringframeworkBootSpringBootLibraryAccessors(owner);

        public OrgSpringframeworkBootSpringLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springframework.boot.spring.boot
         */
        public OrgSpringframeworkBootSpringBootLibraryAccessors getBoot() {
            return laccForOrgSpringframeworkBootSpringBootLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringBootStarterLibraryAccessors laccForOrgSpringframeworkBootSpringBootStarterLibraryAccessors = new OrgSpringframeworkBootSpringBootStarterLibraryAccessors(owner);

        public OrgSpringframeworkBootSpringBootLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springframework.boot.spring.boot.starter
         */
        public OrgSpringframeworkBootSpringBootStarterLibraryAccessors getStarter() {
            return laccForOrgSpringframeworkBootSpringBootStarterLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootStarterLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors laccForOrgSpringframeworkBootSpringBootStarterDataLibraryAccessors = new OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors(owner);

        public OrgSpringframeworkBootSpringBootStarterLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for actuator (org.springframework.boot:spring-boot-starter-actuator)
         * with versionRef 'org.springframework.boot.spring.boot.starter.actuator'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getActuator() {
                return create("org.springframework.boot.spring.boot.starter.actuator");
        }

            /**
             * Creates a dependency provider for hateoas (org.springframework.boot:spring-boot-starter-hateoas)
         * with versionRef 'org.springframework.boot.spring.boot.starter.hateoas'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getHateoas() {
                return create("org.springframework.boot.spring.boot.starter.hateoas");
        }

            /**
             * Creates a dependency provider for test (org.springframework.boot:spring-boot-starter-test)
         * with versionRef 'org.springframework.boot.spring.boot.starter.test'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTest() {
                return create("org.springframework.boot.spring.boot.starter.test");
        }

        /**
         * Returns the group of libraries at org.springframework.boot.spring.boot.starter.data
         */
        public OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors getData() {
            return laccForOrgSpringframeworkBootSpringBootStarterDataLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors extends SubDependencyFactory {

        public OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for jpa (org.springframework.boot:spring-boot-starter-data-jpa)
         * with versionRef 'org.springframework.boot.spring.boot.starter.data.jpa'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJpa() {
                return create("org.springframework.boot.spring.boot.starter.data.jpa");
        }

            /**
             * Creates a dependency provider for rest (org.springframework.boot:spring-boot-starter-data-rest)
         * with versionRef 'org.springframework.boot.spring.boot.starter.data.rest'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRest() {
                return create("org.springframework.boot.spring.boot.starter.data.rest");
        }

    }

    public static class OrgWiremockLibraryAccessors extends SubDependencyFactory {

        public OrgWiremockLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for wiremock (org.wiremock:wiremock)
         * with versionRef 'org.wiremock.wiremock'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getWiremock() {
                return create("org.wiremock.wiremock");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final IoVersionAccessors vaccForIoVersionAccessors = new IoVersionAccessors(providers, config);
        private final OrgVersionAccessors vaccForOrgVersionAccessors = new OrgVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.io
         */
        public IoVersionAccessors getIo() {
            return vaccForIoVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.org
         */
        public OrgVersionAccessors getOrg() {
            return vaccForOrgVersionAccessors;
        }

    }

    public static class IoVersionAccessors extends VersionFactory  {

        private final IoHypersistenceVersionAccessors vaccForIoHypersistenceVersionAccessors = new IoHypersistenceVersionAccessors(providers, config);
        public IoVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.io.hypersistence
         */
        public IoHypersistenceVersionAccessors getHypersistence() {
            return vaccForIoHypersistenceVersionAccessors;
        }

    }

    public static class IoHypersistenceVersionAccessors extends VersionFactory  {

        private final IoHypersistenceHypersistenceVersionAccessors vaccForIoHypersistenceHypersistenceVersionAccessors = new IoHypersistenceHypersistenceVersionAccessors(providers, config);
        public IoHypersistenceVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.io.hypersistence.hypersistence
         */
        public IoHypersistenceHypersistenceVersionAccessors getHypersistence() {
            return vaccForIoHypersistenceHypersistenceVersionAccessors;
        }

    }

    public static class IoHypersistenceHypersistenceVersionAccessors extends VersionFactory  {

        private final IoHypersistenceHypersistenceUtilsVersionAccessors vaccForIoHypersistenceHypersistenceUtilsVersionAccessors = new IoHypersistenceHypersistenceUtilsVersionAccessors(providers, config);
        public IoHypersistenceHypersistenceVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.io.hypersistence.hypersistence.utils
         */
        public IoHypersistenceHypersistenceUtilsVersionAccessors getUtils() {
            return vaccForIoHypersistenceHypersistenceUtilsVersionAccessors;
        }

    }

    public static class IoHypersistenceHypersistenceUtilsVersionAccessors extends VersionFactory  {

        private final IoHypersistenceHypersistenceUtilsHibernateVersionAccessors vaccForIoHypersistenceHypersistenceUtilsHibernateVersionAccessors = new IoHypersistenceHypersistenceUtilsHibernateVersionAccessors(providers, config);
        public IoHypersistenceHypersistenceUtilsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.io.hypersistence.hypersistence.utils.hibernate
         */
        public IoHypersistenceHypersistenceUtilsHibernateVersionAccessors getHibernate() {
            return vaccForIoHypersistenceHypersistenceUtilsHibernateVersionAccessors;
        }

    }

    public static class IoHypersistenceHypersistenceUtilsHibernateVersionAccessors extends VersionFactory  {

        public IoHypersistenceHypersistenceUtilsHibernateVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: io.hypersistence.hypersistence.utils.hibernate.v62 (3.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getV62() { return getVersion("io.hypersistence.hypersistence.utils.hibernate.v62"); }

    }

    public static class OrgVersionAccessors extends VersionFactory  {

        private final OrgApacheVersionAccessors vaccForOrgApacheVersionAccessors = new OrgApacheVersionAccessors(providers, config);
        private final OrgHibernateVersionAccessors vaccForOrgHibernateVersionAccessors = new OrgHibernateVersionAccessors(providers, config);
        private final OrgPostgresqlVersionAccessors vaccForOrgPostgresqlVersionAccessors = new OrgPostgresqlVersionAccessors(providers, config);
        private final OrgProjectlombokVersionAccessors vaccForOrgProjectlombokVersionAccessors = new OrgProjectlombokVersionAccessors(providers, config);
        private final OrgSpringframeworkVersionAccessors vaccForOrgSpringframeworkVersionAccessors = new OrgSpringframeworkVersionAccessors(providers, config);
        private final OrgWiremockVersionAccessors vaccForOrgWiremockVersionAccessors = new OrgWiremockVersionAccessors(providers, config);
        public OrgVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.apache
         */
        public OrgApacheVersionAccessors getApache() {
            return vaccForOrgApacheVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.org.hibernate
         */
        public OrgHibernateVersionAccessors getHibernate() {
            return vaccForOrgHibernateVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.org.postgresql
         */
        public OrgPostgresqlVersionAccessors getPostgresql() {
            return vaccForOrgPostgresqlVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.org.projectlombok
         */
        public OrgProjectlombokVersionAccessors getProjectlombok() {
            return vaccForOrgProjectlombokVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.org.springframework
         */
        public OrgSpringframeworkVersionAccessors getSpringframework() {
            return vaccForOrgSpringframeworkVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.org.wiremock
         */
        public OrgWiremockVersionAccessors getWiremock() {
            return vaccForOrgWiremockVersionAccessors;
        }

    }

    public static class OrgApacheVersionAccessors extends VersionFactory  {

        private final OrgApacheCommonsVersionAccessors vaccForOrgApacheCommonsVersionAccessors = new OrgApacheCommonsVersionAccessors(providers, config);
        public OrgApacheVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.apache.commons
         */
        public OrgApacheCommonsVersionAccessors getCommons() {
            return vaccForOrgApacheCommonsVersionAccessors;
        }

    }

    public static class OrgApacheCommonsVersionAccessors extends VersionFactory  {

        private final OrgApacheCommonsCommonsVersionAccessors vaccForOrgApacheCommonsCommonsVersionAccessors = new OrgApacheCommonsCommonsVersionAccessors(providers, config);
        public OrgApacheCommonsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.apache.commons.commons
         */
        public OrgApacheCommonsCommonsVersionAccessors getCommons() {
            return vaccForOrgApacheCommonsCommonsVersionAccessors;
        }

    }

    public static class OrgApacheCommonsCommonsVersionAccessors extends VersionFactory  {

        public OrgApacheCommonsCommonsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.apache.commons.commons.lang3 (3.13.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getLang3() { return getVersion("org.apache.commons.commons.lang3"); }

    }

    public static class OrgHibernateVersionAccessors extends VersionFactory  {

        private final OrgHibernateValidatorVersionAccessors vaccForOrgHibernateValidatorVersionAccessors = new OrgHibernateValidatorVersionAccessors(providers, config);
        public OrgHibernateVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.hibernate.validator
         */
        public OrgHibernateValidatorVersionAccessors getValidator() {
            return vaccForOrgHibernateValidatorVersionAccessors;
        }

    }

    public static class OrgHibernateValidatorVersionAccessors extends VersionFactory  {

        private final OrgHibernateValidatorHibernateVersionAccessors vaccForOrgHibernateValidatorHibernateVersionAccessors = new OrgHibernateValidatorHibernateVersionAccessors(providers, config);
        public OrgHibernateValidatorVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.hibernate.validator.hibernate
         */
        public OrgHibernateValidatorHibernateVersionAccessors getHibernate() {
            return vaccForOrgHibernateValidatorHibernateVersionAccessors;
        }

    }

    public static class OrgHibernateValidatorHibernateVersionAccessors extends VersionFactory  {

        public OrgHibernateValidatorHibernateVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.hibernate.validator.hibernate.validator (7.0.5.Final)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getValidator() { return getVersion("org.hibernate.validator.hibernate.validator"); }

    }

    public static class OrgPostgresqlVersionAccessors extends VersionFactory  {

        public OrgPostgresqlVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.postgresql.postgresql (42.6.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getPostgresql() { return getVersion("org.postgresql.postgresql"); }

    }

    public static class OrgProjectlombokVersionAccessors extends VersionFactory  {

        public OrgProjectlombokVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.projectlombok.lombok (1.18.30)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getLombok() { return getVersion("org.projectlombok.lombok"); }

    }

    public static class OrgSpringframeworkVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootVersionAccessors vaccForOrgSpringframeworkBootVersionAccessors = new OrgSpringframeworkBootVersionAccessors(providers, config);
        public OrgSpringframeworkVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springframework.boot
         */
        public OrgSpringframeworkBootVersionAccessors getBoot() {
            return vaccForOrgSpringframeworkBootVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringVersionAccessors vaccForOrgSpringframeworkBootSpringVersionAccessors = new OrgSpringframeworkBootSpringVersionAccessors(providers, config);
        public OrgSpringframeworkBootVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springframework.boot.spring
         */
        public OrgSpringframeworkBootSpringVersionAccessors getSpring() {
            return vaccForOrgSpringframeworkBootSpringVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringBootVersionAccessors vaccForOrgSpringframeworkBootSpringBootVersionAccessors = new OrgSpringframeworkBootSpringBootVersionAccessors(providers, config);
        public OrgSpringframeworkBootSpringVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springframework.boot.spring.boot
         */
        public OrgSpringframeworkBootSpringBootVersionAccessors getBoot() {
            return vaccForOrgSpringframeworkBootSpringBootVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringBootStarterVersionAccessors vaccForOrgSpringframeworkBootSpringBootStarterVersionAccessors = new OrgSpringframeworkBootSpringBootStarterVersionAccessors(providers, config);
        public OrgSpringframeworkBootSpringBootVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springframework.boot.spring.boot.starter
         */
        public OrgSpringframeworkBootSpringBootStarterVersionAccessors getStarter() {
            return vaccForOrgSpringframeworkBootSpringBootStarterVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootStarterVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringBootStarterDataVersionAccessors vaccForOrgSpringframeworkBootSpringBootStarterDataVersionAccessors = new OrgSpringframeworkBootSpringBootStarterDataVersionAccessors(providers, config);
        public OrgSpringframeworkBootSpringBootStarterVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.springframework.boot.spring.boot.starter.actuator (3.2.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getActuator() { return getVersion("org.springframework.boot.spring.boot.starter.actuator"); }

            /**
             * Returns the version associated to this alias: org.springframework.boot.spring.boot.starter.hateoas (3.2.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getHateoas() { return getVersion("org.springframework.boot.spring.boot.starter.hateoas"); }

            /**
             * Returns the version associated to this alias: org.springframework.boot.spring.boot.starter.test (3.2.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTest() { return getVersion("org.springframework.boot.spring.boot.starter.test"); }

        /**
         * Returns the group of versions at versions.org.springframework.boot.spring.boot.starter.data
         */
        public OrgSpringframeworkBootSpringBootStarterDataVersionAccessors getData() {
            return vaccForOrgSpringframeworkBootSpringBootStarterDataVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootStarterDataVersionAccessors extends VersionFactory  {

        public OrgSpringframeworkBootSpringBootStarterDataVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.springframework.boot.spring.boot.starter.data.jpa (3.2.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJpa() { return getVersion("org.springframework.boot.spring.boot.starter.data.jpa"); }

            /**
             * Returns the version associated to this alias: org.springframework.boot.spring.boot.starter.data.rest (3.2.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getRest() { return getVersion("org.springframework.boot.spring.boot.starter.data.rest"); }

    }

    public static class OrgWiremockVersionAccessors extends VersionFactory  {

        public OrgWiremockVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.wiremock.wiremock (3.3.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getWiremock() { return getVersion("org.wiremock.wiremock"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

}
