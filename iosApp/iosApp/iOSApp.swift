import SwiftUI

private var launchStart: Date!
private var didLogTTID = false

@main
struct iOSApp: App {
    init() {
        launchStart = Date()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
                .onAppear {
                    if !didLogTTID {
                        didLogTTID = true
                        DispatchQueue.main.async {
                            let ttid = Date().timeIntervalSince(launchStart)
                            print("🔥🔥🔥 TTID: \(ttid) seconds")
                        }
                    }
                }
        }
    }
}
