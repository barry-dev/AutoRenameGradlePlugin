package com.barry.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import java.util.regex.Matcher

class RenameAppVersionNameTask extends DefaultTask {

  @TaskAction
  def run() {
    project.configure(project) {
      if (it.hasProperty("android")) {
        project.android.applicationVariants.all { variant ->
          if (variant.buildType.name == project.android.buildTypes.debug.name) {
            renameDebugAppVersionName(variant)
          }
        }
      }
    }
  }

  RenameAppVersionNameTask() {
    group = 'customPlugin'
    description = 'Renames versionName of the app depends on the current git branch name'
  }

  static def renameDebugAppVersionName(variant) {
    def customVersionName = variant.mergedFlavor.versionName + "-dev" // Or Use getCurrentBranchCodeName() here
    variant.mergedFlavor.versionName = customVersionName
    println "${variant.name} version name: ${customVersionName}"
  }

  static def getCurrentBranchCodeName() {
    def currentBranchName = 'git rev-parse --abbrev-ref HEAD'.execute().text.trim()
    String branchTicketCode = "";
    Matcher matcher = currentBranchName =~ /write here your regex/
    if (matcher.size() > 0) {
      branchTicketCode = matcher[0][1]
    }
    return branchTicketCode
  }
}