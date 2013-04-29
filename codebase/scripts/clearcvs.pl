$os = lc($^O);
$isWindows = ($os =~ /win/);
#print "$isWindows\n";
clear($ARGV[0]);

sub clear {
  my $file = $_[0];
  if (-d $file) {
    opendir(DIR, $file) || die ("could not open dir $file");
    my @nextFiles = readdir(DIR);
    closedir(DIR);

    my $nextFile = "";
    foreach $nextFile (@nextFiles) {
      if ($nextFile eq "." || $nextFile eq "..") { next; }
      clear("$file/$nextFile");
    }

    if ($file =~ /\/CVS$/) {
      print "Removing ".convertFileName($file)."\n";
      rmdir(convertFileName($file));
    }
  } else {
    if ($file =~ /\/CVS\//) {
      print "Removing ".convertFileName($file)."\n";
      unlink(convertFileName($file));
    }
  }
}

sub convertFileName {
  my $file = $_[0];
  if ($isWindows) { 
    $file =~ s/\//\\/g;
  }
  return $file;
}