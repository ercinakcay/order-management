prepare-test: prepare test

prepare-package: prepare package

build-dev: prepare-package compose-dev rest-a-bit sanity-check-dev
build-test: prepare-package compose-test rest-a-bit sanity-check-test
build-prod: prepare-package compose-prod rest-a-bit sanity-check-prod

dev: rm compose-with-tail

# prepare the project dependencies and plugins.
prepare:
		sh mvnw clean install -Dmaven.test.skip=true

rest-a-bit:
		@echo "Waiting the containers..."
		sleep 15

package:
		sh mvnw package -Dmaven.test.skip=true

# sanity check request which shows cool message while project up.
define sanity-check
        curl -XGET http://localhost:$(1)/checkSanity
endef

# run 'docker-compose up' command for {../order-management-api/docker-compose-{}.yml} path
define docker-compose-up
        docker-compose -f ../order-management-api/docker-compose.$(1).yml up -d
endef

sanity-check-dev:
		$(call  sanity-check,8080)

sanity-check-test:
		$(call  sanity-check,8083)

sanity-check-prod:
		$(call  sanity-check,8082)

compose-prod:
		$(call  docker-compose-up,prod)

compose-test:
		$(call  docker-compose-up,test)

compose-dev:
		$(call  docker-compose-up,dev)


# DEVELOPER HELPERS
#
# added for debug purpose
compose-with-tail:
		docker-compose -f ../order-management-api/docker-compose.dev.yml up

rm:
        ifneq ("",$(docker images -a | grep order-management-api |cut -f 1 -d ' ' | xargs -n 1))
				docker images -a | grep order-management-api |cut -f 1 -d ' ' | xargs -n 1 docker rmi -f
        endif

