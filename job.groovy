pipelineJob('DSL_Pipeline') {
  
  properties {
    disableConcurrentBuilds()  
  }

  def repo = 'https://github.com/Pradipta-P/sonar.git'

  triggers {
    scm('H/5 * * * *')
  }
  description("Pipeline for $repo")

  definition {
    cpsScm {
      scm {
        git {
          remote { url(repo) }
          branches('master')
          scriptPath('Jenkinsfile')
          extensions { }  // required as otherwise it may try to tag the repo, which you may not want
        }

        // the single line below also works, but it
        // only covers the 'master' branch and may not give you
        // enough control.
        // git(repo, 'master', { node -> node / 'extensions' << '' } )
      }
    }
  }
}
