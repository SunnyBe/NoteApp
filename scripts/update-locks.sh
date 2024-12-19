#!/bin/bash

# Base directory for lockfiles
lockfile_dir="./gradle/lockfile"

# List of subprojects
subprojects=(
    "build-logic:convention"
    ":app"
    ":library:common-resource"
    ":library:common-config"
    ":library:testing"
    ":library:design_system"
    ":feature:note"
)

# Function to delete lockfiles
delete_lockfiles() {
  echo "Deleting all existing lockfiles in $lockfile_dir..."
  if [[ -d "$lockfile_dir" ]]; then
    rm -f "$lockfile_dir"/*.lockfile
    if [[ $? -eq 0 ]]; then
      echo "All lockfiles deleted successfully."
    else
      echo "Failed to delete some lockfiles."
      exit 1
    fi
  else
    echo "Lockfile directory does not exist: $lockfile_dir"
  fi
}

# Function to generate lockfiles
generate_lockfiles() {
  echo "Generating lockfiles for the following subprojects:"
  for subproject in "${subprojects[@]}"; do
    echo "- $subproject"
    ./gradlew "$subproject:dependencies" --write-locks
    if [[ $? -ne 0 ]]; then
      echo "Failed to generate lockfile for $subproject"
      exit 1
    else
      echo "Lockfile generated for $subproject"
    fi
  done
}

# Main script logic
if [[ "$1" == "--delete" ]]; then
  delete_lockfiles
else
  echo "Skipping lockfile deletion (use --delete to delete existing lockfiles)."
  generate_lockfiles
  echo "Lock files generation for selected modules is complete!"
fi
