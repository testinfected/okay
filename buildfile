define 'okay', :group => 'com.vtence.okay', :version => '0.1-SNAPSHOT' do
  compile.options.source = '1.8'
  compile.options.target = '1.8'

  compile.with
  test.with :hamcrest, :hamcrest_junit, :junit
  package :jar

  package_with_javadoc
  package_with_sources
end
