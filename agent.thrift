namespace cpp com.xujl.ipagent
namespace d com.xujl.ipagent
namespace java com.xujl.ipagent
namespace php com.xujl.ipagent
namespace perl com.xujl.ipagent
namespace as3 com.xujl.ipagent

service AgentService {
   string disable(string domain,string ip),
   string enrol(string domain, i32 type,  string url, string rule,i64 interval) ,
   string get(string domain) ,
   i32 getAvailableByDomain(string domain)
}