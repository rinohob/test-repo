# SHRSS AEM project

This is a project for AEM-based SHRSS applications. 

## Modules

The main parts of the template are:

* core: Java bundle containing all core functionality like OSGi services, listeners or schedulers, as well as component-related Java code such as servlets or request filters.
* it.tests: Java based integration tests
* ui.apps: contains the /apps (and /etc) parts of the project, ie JS&CSS clientlibs, components, and templates
* ui.content: contains sample content using the components from the ui.apps
* ui.config: contains runmode specific OSGi configs for the project
* ui.frontend: an optional dedicated front-end build mechanism (Angular, React or general Webpack project)
* ui.tests: Selenium based UI tests
* all: a single content package that embeds all of the compiled modules (bundles and content packages) including any vendor dependencies
* analyse: this module runs analysis on the project which provides additional validation for deploying into AEMaaCS

## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

To build all the modules and deploy the `all` package to a local instance of AEM, run in the project root directory the following command:

    mvn clean install -PautoInstallSinglePackage

Or to deploy it to a publish instance, run

    mvn clean install -PautoInstallSinglePackagePublish

Or alternatively

    mvn clean install -PautoInstallSinglePackage -Daem.port=4503

Or to deploy only the bundle to the author, run

    mvn clean install -PautoInstallBundle

Or to deploy only a single content package, run in the sub-module directory (i.e `ui.apps`)

    mvn clean install -PautoInstallPackage

## Testing

There are three levels of testing contained in the project:

### Unit tests

This show-cases classic unit testing of the code contained in the bundle. To
test, execute:

    mvn clean test

### Integration tests

This allows running integration tests that exercise the capabilities of AEM via
HTTP calls to its API. To run the integration tests, run:

    mvn clean verify -Plocal

Test classes must be saved in the `src/main/java` directory (or any of its
subdirectories), and must be contained in files matching the pattern `*IT.java`.

The configuration provides sensible defaults for a typical local installation of
AEM. If you want to point the integration tests to different AEM author and
publish instances, you can use the following system properties via Maven's `-D`
flag.

| Property | Description | Default value |
| --- | --- | --- |
| `it.author.url` | URL of the author instance | `http://localhost:4502` |
| `it.author.user` | Admin user for the author instance | `admin` |
| `it.author.password` | Password of the admin user for the author instance | `admin` |
| `it.publish.url` | URL of the publish instance | `http://localhost:4503` |
| `it.publish.user` | Admin user for the publish instance | `admin` |
| `it.publish.password` | Password of the admin user for the publish instance | `admin` |

The integration tests in this archetype use the [AEM Testing
Clients](https://github.com/adobe/aem-testing-clients) and showcase some
recommended [best
practices](https://github.com/adobe/aem-testing-clients/wiki/Best-practices) to
be put in use when writing integration tests for AEM.

## Static Analysis

The `analyse` module performs static analysis on the project for deploying into AEMaaCS. It is automatically
run when executing

    mvn clean install

from the project root directory. Additional information about this analysis and how to further configure it
can be found here https://github.com/adobe/aemanalyser-maven-plugin

### UI tests

They will test the UI layer of your AEM application using Selenium technology. 

To run them locally:

    mvn clean verify -Pui-tests-local-execution

This default command requires:
* an AEM author instance available at http://localhost:4502 (with the whole project built and deployed on it, see `How to build` section above)
* Chrome browser installed at default location

Check README file in `ui.tests` module for more details.

## ClientLibs

The frontend module is made available using an [AEM ClientLib](https://helpx.adobe.com/experience-manager/6-5/sites/developing/using/clientlibs.html). When executing the NPM build script, the app is built and the [`aem-clientlib-generator`](https://github.com/wcm-io-frontend/aem-clientlib-generator) package takes the resulting build output and transforms it into such a ClientLib.

A ClientLib will consist of the following files and directories:

- `css/`: CSS files which can be requested in the HTML
- `css.txt` (tells AEM the order and names of files in `css/` so they can be merged)
- `js/`: JavaScript files which can be requested in the HTML
- `js.txt` (tells AEM the order and names of files in `js/` so they can be merged
- `resources/`: Source maps, non-entrypoint code chunks (resulting from code splitting), static assets (e.g. icons), etc.

## Maven settings

The project comes with the auto-public repository configured. To setup the repository in your Maven settings, refer to:

    http://helpx.adobe.com/experience-manager/kb/SetUpTheAdobeMavenRepository.html
	

### Development Methodology/Process

* Below section outlines the development method/process that will be followed for SHRSS implementation.

* SSD & Sprint Stories will be the bible for developers all the requirements will be tracked against the Stories.
* PO and Dev Lead to baseline and deploy Sprint Stories. Complete team (onshore/offshore) to refer for the current sprint.
* Any new changes in existing stories by PO / FE needs to get into refactoring story.
* Any bug fixes or critical changes need to be sent to Lead and he will validate and create new story /Defect with the help of PO.
* Lead to communicate the respective dev for fix availability.
* Developers to create story branch from develop.
* Developers to keep work in progress in story branch.
* When done developers to raise PR to develop. Move story to Code Review.
* Dev Lead / TA to code review and merge if ok. Move story to In Cyclone.
* Developers to validate SONAR and integration issues. Fix and again raise PR. Move story to Code Review.
* Testers to write authoring guide. Developers to give cyclone on DEV. 
* When done developer to raise PR for promoting develop to QA branch. 
* Dev Lead to merge the PR and move story to dev complete.
* Lead/QA to trigger the build and when success move the story to In validate.
* QA to validate and raise defects. When fixed, QA to record demo. Send recording to PO and move story to Complete.
* PO to review demo and mark story to accepted or move to backlog or create a new refactoring story.

#### Branch Terminology

* 'repository': the main git repsitory currently located at https://github.com/seminolehardrock/shrss-aem-projects
* 'main' branch - branch that will be used to build for SHRSS stage & Prod environment.
* 'QA' branch - branch that will be used to build for SHRSS QA environment.
* 'develop' branch - branch that will be used to build for  development environment. 
* 'feature' - branch prefixed will be denote the user story number and will be used for development of that particular story. Convention will be ex: SHRSS-1234
* 'bugfix' branch - branch that will be created from master for providing hot fixes post release. Convention will be BUGFIX_DEFECTID
* 'coderefactoring' branch - branch that will be created from develop for providing generic sonar fixes and code refactoring/cleanup. 

#### Branching and Merging

* A feature branch can only be created from develop branch.
* A feature can only be merged into develop branch through a PR.
* Code can be merged to release branch only through a PR. Usually this will be from develop branch.
* Code can be merged to master branch only through a PR. Usually this will be from release branch.

#### Basic commands for branching and merging

* Clone:

```
git clone git@github.com:seminolehardrock/shrss-aem-projects.git
```

* Switch to develop branch:

```
git checkout develop
```

* Create a local feature branch:

```
git checkout -b feature/SHRSS-1234 develop
```

* Create remote branch and point local to remote:

```
git push --set-upstream origin feature/SHRSS-1234
```

* Pull changes from develop:

```
git pull
```

* Merge changes from develop:

```	
git merge develop
```

* Commit to local feature changes to HEAD:

```
git commit -a -m "Feature feature/SHRSS-1234"
```

* Commit feature HEAD changes to remote origin:

```
git push origin
```

#### Assumptions/Bindings/Guidelines

* No feature commit can be done directly to 'develop' branche. Feature will only go in this branch via pull requests (PR). However non-code artifacts (such as this README) can be checked in to develop by a TA or TL.
* No commit can be done directly to 'release' or 'main' branch. Only exception to main is the initial bare metal code base as this is the default branch and starting place. Revisions to these branches can only be done via PR.
* As far as possible, one PR per feature or bug.
* Meaningful description to be provided on all PR.
* Meaningful description to be provided on all commits.
* Code review will be done on PR and review comments will be provided on PR itself.
* PR for release will only be accepted after SONAR issues are resolved or marked deferred.
* Peer developers shall review an open PR and provide inputs within the Git interface
* All developers who are done reviewing the PR - must post in a comment that they are done with review
* Developer will address the review comments by pushing more commits to the pull request
* Once all commits are addressed, the pull-request shall be merged
* Each PR must be reviewed - and a comment for the same must be entered

#### Code Review focus

Focus on reviews will be more on the 
* algorithm
* coding best practices
* performance
* resource-leak 
* null-pointers

#### General Coding Guidelines:-
* All constants must be added to existing SHRSSConstants.java. 
* All Utility methods to be used in services, servlets, etc must be written in existing LinkUtils.java
* All methods must be preceded by proper Javadoc. 
* All constants must have a proper and detailed Javadoc
* Proper description for each parameter and return type in Javadoc must be added.
* Method names and Parameter names in methods must be self explanatory. Arguments such as param1, param2 must not be given.
* Logger variable must be decalred as LOGGER. So in code it should appear as LOGGER.debug
* Each catch block must have LOGGER.error
* Each Resource obtained from Repo must be null checked, especially after adaptTo, etc
* Code must not have any Sonar defects, vulnerabilities, code smells, etc
* Servlets must return proper HTTP codes in case of error, or success with appropriate messages
* Ajax requests must have handling for success and error blocks
* Minimum Java classes should be created as far as possible. Make use of Sightly features that allow you to use implicit objects, SlingModels, etc. 
* No exception consummation (sink) in middle of code. Proper response or exception must be floated to caller for proper handling.
* Every if must have an else. If no action required in else then at minimum LOGGER.debug must be present.
* For common files like SHRSSConstants and LinkUtils, do not correct indentation so that the PR should only tell the change that is actually done.
* Use i18N dictionary for display labels on html
* No global variables to be defined in servlet, especially if it is not final.
* Please use proper javadocs. Validate the same using mvn javadocs:javadocs command before raising PR.
* ERROR log level should be used in exception blocks and critical checks after which code cannot execute and has to return abruptly. Rest everywhere use DEBUG log level.
* Do not print PII (Personally Identifiable Information) in logs. This also includes for passwords, email id, etc.
* In case you are extending/customizing any OOTB java code, HTL etc, try to do by using Javascript first.
* Please discuss with dev lead/ TA before creating a new package.
* Connect with senior members if you do not know how to achieve any of above
* Any deviations from above needs to be approved by Dev Lead /TA

#### Coding guidelines for package structuring and what goes where
* All servlets should be in com.shrss.core.servlets package
* All service interfaces should be in com.shrss.core.services package
* All service implementations should be in com.shrss.core.services.impl package
* All servlets names must suffix "Servlet", services suffix "Service", service impl suffix "ServiceImpl", etc
* All constants must be in "SHRSSConstants.java"
* All utility methods must be in "LinkUtils.java"
* All sightly utils must be in "SightlyUtils.java"
* Do not create a POJO (WCMUsePojo) use sling models
* All workflow steps should be in com.shrss.core.workflows package
* All Models should be in com.shrss.core.models & com.shrss.core.models.impl package
* If a constant is already present, use that instead of creating a new constant
* Demarcate your constants with java comment block //Start - Asset metadata constants and //End - Asset metadata constants
* Keep constant name as generic as possible and not tie them to your code/class/component name - unless absolutely necessary
* If required add a constant in middle of a file. Its not required to add a constant only at the end
* LinkUtils should not have any injections or dependencies of a service.
* Each method in LinkUtils will only work on parameter passed and return a mutated response.
* No class level variables in a servlet
* Methods in LinkUtils should be very generic and really reusable. Example methods in LinkUtils could be createZip, calculateChecksum, etc. 
* A method in LinkUtils cannot call a service method. Vice versa will be true.
* Each service must have an interface that will be exposed to other packages
* Each service must have an Impl which will be hidden
* Internal methods within an Impl will be protected access modifier
* Refrain from creating private methods anywhere in code. Take explicit approval of Dev Lead in case required.
* Service Interface will only contain business methods like getAssetsInCollection, getUserCollections. Do not put methods like randomizeSting in service. LinkUtils is proper place for such a method.
* Before creating a new method in LinkUtils, search and see if any such method already exists in LinkUtils
* Proper and elaborate javadoc comments are must for methods in LinkUtils
* Services can be injected in Models, Servlets
* Never call a servlet method from any other class. This is abuse and violation of basic coding practices.
* Never overload a servlet method such as doGet or doPost. Create a separate servlet in such a requirement scenario.
* A servlet should conform to REST principles - Use doGet for query, doPost for Save, etc. 
* Use resource type & selector based approach for creating a servlet. Path based approach only where directed.
* Consider caching and non caching requirement in a servlet.