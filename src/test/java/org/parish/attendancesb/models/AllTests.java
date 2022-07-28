package org.parish.attendancesb.models;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("models.datetime")
@SelectPackages("org.parish.attendancesb.models")
@IncludeClassNamePatterns(".*Test")
public class AllTests {
}
