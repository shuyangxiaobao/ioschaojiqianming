require "spaceship"

Spaceship.login('', '')

#参数传入true表示需要新增设备，例如：ruby UpdateProfile.rb true
if ARGV[0] == "true"
    file = File.open("/Users/xiaobao/java/Tomcat/apache-tomcat-8.5.51/bin/apex.txt")
    puts "Updating #{p}"

    file.each do |line|
        arr = line.strip.split("\t")
        device = Spaceship.device.create!(name: arr[1], udid: arr[0])
        puts "add device: #{device.name} #{device.udid} #{device.model}"
    end

    devices = Spaceship.device.all
    puts "length:: #{devices.length}"

    devices.each do |line|
      puts "udid:#{line.udid} name:#{line.name}"
    end


    profiles = Array.new
    profiles += Spaceship.provisioning_profile.development.all
#    profiles += Spaceship.provisioning_profile.ad_hoc.all

    profiles.each do |p|
        if "#{p.name}" == "APEX_dev" || "#{p.name}" == "APEX_Notfication_dev"
          if devices.length < 50
            puts "Updating #{p.name}"
            p.devices = devices
            p.update!
          end
        end
    end
end

# dic = {"qdinfini.com.cn.dev" => "qdinfini.com.cn.dev.mobileprovision",
#     "qdinfini.com.cn2.dev" => "qdinfini.com.cn2.dev.mobileprovision",
#     "qdinfini.com.cn2.NotificationService.dev" => "qdinfini.com.cn2.NotificationService.dev.mobileprovision",
#     "qdinfini.com.NotificationService.dev" => "qdinfini.com.NotificationService.dev.mobileprovision",
#     "wuchanjinshu.dev" => "wuchanjinshu.dev.mobileprovision"
# }

downloadProfiles = Array.new
downloadProfiles += Spaceship.provisioning_profile.development.all
#downloadProfiles += Spaceship.provisioning_profile.ad_hoc.all

downloadProfiles.each do |p|
 #   fileName = dic[p.name]
     if "#{p.name}" == "APEX_dev" || "#{p.name}" == "APEX_Notfication_dev"
            puts "Downloading #{p.name}"
            File.write("/Users/xiaobao/chaojiqianming/#{p.name}.mobileprovision", p.download)
      end
end
